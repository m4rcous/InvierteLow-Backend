package com.example.inviertelow.platform.letra.domain.model.aggregates;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import com.example.inviertelow.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class CarteraLetra extends AuditableAbstractAggregateRoot<CarteraLetra> {
    @OneToMany(mappedBy = "carteraLetra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Letra> letras = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "carteraLetra", orphanRemoval = true)
    private CalculoCarteraLetra calculoCarteraLetra;

    public CarteraLetra() {
    }

    public void agregarLetra(Letra letra) {
        letra.setCarteraLetra(this);
        this.letras.add(letra);
    }

    public void removerLetra(Letra letra) {
        letra.setCarteraLetra(null);
        this.letras.remove(letra);
    }

    public void calcularCartera() {
        if (this.calculoCarteraLetra == null) {
            this.calculoCarteraLetra = new CalculoCarteraLetra(this);
        } else {
            this.calculoCarteraLetra.recalcular();
        }
    }
}
