package com.example.inviertelow.platform.letra.domain.model.entities;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.PlazoDeTasa;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.TipoDeTasa;
import com.example.inviertelow.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Entity
public class CalculoLetra extends AuditableModel {

    @OneToOne
    @JoinColumn(name = "letra_id")
    private Letra letra;

    private int numeroDiasTranscurridos;

    private BigDecimal retencion;
    private BigDecimal costosInicialesTotales;
    private BigDecimal costosFinalesTotales;
    private BigDecimal tasaCostoEfectivaAnual = BigDecimal.ZERO;
    private BigDecimal tasaDescontadaPeriodo = BigDecimal.ZERO;
    private BigDecimal tasaEfectivaPeriodo = BigDecimal.ZERO;
    private BigDecimal descuento = BigDecimal.ZERO;
    private BigDecimal valorNeto = BigDecimal.ZERO;
    private BigDecimal valorRecibido = BigDecimal.ZERO;
    private BigDecimal valorEntregado = BigDecimal.ZERO;
    private BigDecimal tcea = BigDecimal.ZERO;

    public CalculoLetra() {
    }

    public CalculoLetra(Letra letra) {
        this.letra = letra;
        this.numeroDiasTranscurridos = calcularNumeroDiasTranscurridos();
        this.retencion = letra.getRetencion().getValor();

        this.tasaEfectivaPeriodo = calcularTasaEfectivaPeriodo();
        this.tasaDescontadaPeriodo = calcularTasaDescontadaPeriodo();
        this.descuento = calcularDescuento();
        this.valorNeto = calcularValorNeto();
        this.valorRecibido = calcularValorRecibido();
        this.valorEntregado = calcularValorEntregado();
        this.tcea = calcularTCEA();

        BigDecimal sumaCostosIniciales = letra.getCostos().stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.INICIAL)
                .map(costo -> costo.getMonto().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal sumaCostosFinales = letra.getCostos().stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.FINAL)
                .map(costo -> costo.getMonto().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.costosInicialesTotales = sumaCostosIniciales;
        this.costosFinalesTotales = sumaCostosFinales;
    }

    // Calcular dias transcurridos
    private int calcularNumeroDiasTranscurridos() {
        return (int) ChronoUnit.DAYS.between(
                letra.getFechaGiro().getFecha(),
                letra.getFechaVencimiento().getFecha()
        );
    }

    // Calcular tasa efectiva periodo

    private int obtenerPeriodosPorAnio(PlazoDeTasa.Plazo frecuenciaCapitalizacion) {
        return switch (frecuenciaCapitalizacion) {
            case DIARIO -> 360;
            case SEMANAL -> 52;
            case MENSUAL -> 12;
            case BIMESTRAL -> 6;
            case TRIMESTRAL -> 4;
            case SEMESTRAL -> 2;
            case ANUAL -> 1;
            default -> throw new IllegalArgumentException("Frecuencia de capitalización no soportada");
        };
    }

    public BigDecimal calcularTasaEfectivaPeriodo() {
        BigDecimal tasa = letra.getTasa().getValor();
        int diasTranscurridos = calcularNumeroDiasTranscurridos();
        int diasPorAnio = letra.getDiasPorAnio().getDias();
        TipoDeTasa tipoDeTasa = letra.getTipoDeTasa();
        PlazoDeTasa.Plazo frecuenciaCapitalizacion = letra.getPlazoDeTasa().getPlazo();

        BigDecimal tep;

        if (tipoDeTasa.getTipo() == TipoDeTasa.Tipo.NOMINAL) {
            int periodosPorAnio = obtenerPeriodosPorAnio(frecuenciaCapitalizacion);
            tep = BigDecimal.valueOf(Math.pow(1 + tasa.doubleValue() / periodosPorAnio, (double) diasTranscurridos / (diasPorAnio / periodosPorAnio)) - 1);
        } else {
            tep = BigDecimal.valueOf(Math.pow(1 + tasa.doubleValue(), (double) diasTranscurridos / diasPorAnio) - 1);
        }
        return tep;
    }

    // Calcular tasa descontada
    public BigDecimal calcularTasaDescontadaPeriodo() {
        BigDecimal tasaEfectivaPeriodo = calcularTasaEfectivaPeriodo();
        return tasaEfectivaPeriodo.divide(BigDecimal.ONE.add(tasaEfectivaPeriodo), MathContext.DECIMAL64);
    }

    // Calcular descuento
    public BigDecimal calcularDescuento() {
        BigDecimal valorNominal = letra.getValorNominal().getValor();
        BigDecimal tasaDescontadaPeriodo = calcularTasaDescontadaPeriodo();
        return valorNominal.multiply(tasaDescontadaPeriodo).setScale(2, RoundingMode.HALF_UP);
    }

    // Calcular valor neto
    public BigDecimal calcularValorNeto() {
        BigDecimal valorNominal = letra.getValorNominal().getValor();
        BigDecimal descuento = calcularDescuento();
        return valorNominal.subtract(descuento).setScale(2, RoundingMode.HALF_UP);
    }

    // Calcular valor recibido
    public BigDecimal calcularValorRecibido() {
        BigDecimal valorNeto = calcularValorNeto();
        BigDecimal retencion = letra.getRetencion().getValor();
        BigDecimal sumaCostosIniciales = letra.getCostos().stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.INICIAL)
                .map(costo -> costo.getMonto().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return valorNeto.subtract(sumaCostosIniciales).subtract(retencion).setScale(2, RoundingMode.HALF_UP);
    }

    // Calcular valor entregado
    public BigDecimal calcularValorEntregado() {
        BigDecimal valorNominal = letra.getValorNominal().getValor();
        BigDecimal sumaCostosFinales = letra.getCostos().stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.FINAL)
                .map(costo -> costo.getMonto().getValor())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal retencion = letra.getRetencion().getValor();

        return valorNominal.add(sumaCostosFinales).subtract(retencion);
    }

    // Calcular TCEA
    public BigDecimal calcularTCEA() {
        BigDecimal valorEntregar = calcularValorEntregado();
        BigDecimal valorRecibido = calcularValorRecibido();
        int diasTranscurridos = calcularNumeroDiasTranscurridos();

        if (valorRecibido.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("El valor recibido no puede ser cero al calcular la TCEA");
        }

        BigDecimal exponent = BigDecimal.valueOf(360.0).divide(BigDecimal.valueOf(diasTranscurridos), MathContext.DECIMAL128);
        BigDecimal base = valorEntregado.divide(valorRecibido, MathContext.DECIMAL128);
        BigDecimal potencia = BigDecimalMath.pow(base, exponent, MathContext.DECIMAL128);

        BigDecimal tcea = potencia.subtract(BigDecimal.ONE);

        return tcea;
    }
}
