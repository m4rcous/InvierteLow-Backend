package com.example.inviertelow.platform.letra.domain.model.entities;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;


@Getter
@Setter
@Entity
public class CalculoCarteraLetra extends AuditableModel {
    @OneToOne
    @JoinColumn(name = "cartera_letra_id")
    private CarteraLetra carteraLetra;

    @Column(precision = 18, scale = 2)
    private BigDecimal valorTotalRecibido = BigDecimal.ZERO;

    @Column(precision = 18, scale = 6)
    private BigDecimal tceaCartera = BigDecimal.ZERO;

    public CalculoCarteraLetra() {
    }

    public CalculoCarteraLetra(CarteraLetra carteraLetra) {
        this.carteraLetra = carteraLetra;
        realizarCalculos();
    }

    public void recalcular() {
        realizarCalculos();
    }

    private void realizarCalculos() {
        calcularValorTotalRecibido();
        calcularTCEACartera();
    }

    private void calcularValorTotalRecibido() {
        List<Letra> letras = carteraLetra.getLetras();
        this.valorTotalRecibido = letras.stream()
                .map(letra -> letra.getCalculoLetra().getValorRecibido())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void calcularTCEACartera() {
        List<Letra> letras = carteraLetra.getLetras();

        BigDecimal valorEntregadoTotal = letras.stream()
                .map(letra -> letra.getCalculoLetra().getValorEntregado())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorRecibidoTotal = this.valorTotalRecibido;

        // Calcular el plazo promedio ponderado (Npp)
        BigDecimal sumaProductoNiVEi = letras.stream()
                .map(letra -> BigDecimal.valueOf(letra.getCalculoLetra().getNumeroDiasTranscurridos())
                        .multiply(letra.getCalculoLetra().getValorEntregado()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal Npp = sumaProductoNiVEi.divide(valorEntregadoTotal, MathContext.DECIMAL128);

        if (valorRecibidoTotal.compareTo(BigDecimal.ZERO) == 0 || Npp.compareTo(BigDecimal.ZERO) == 0) {
            this.tceaCartera = BigDecimal.ZERO;
            return;
        }

        BigDecimal exponent = BigDecimal.valueOf(360.0).divide(Npp, MathContext.DECIMAL128);
        BigDecimal base = valorEntregadoTotal.divide(valorRecibidoTotal, MathContext.DECIMAL128);

        BigDecimal potencia = BigDecimalMath.pow(base, exponent, MathContext.DECIMAL128);

        this.tceaCartera = potencia.subtract(BigDecimal.ONE).setScale(6, RoundingMode.HALF_UP);
    }
}
