package it.apuliadigital.fidelity.model;

import java.util.Objects;

import it.apuliadigital.fidelity.sevice.Interface.ICard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Card implements ICard {
    private static int count = 1;

    @Id
    private int numTessera;
    private int saldoPunti;
    private String codFisc;

    public Card() {
    }

    public Card( int saldoPunti, String codFisc) {
        this.numTessera = count++;
        this.saldoPunti = saldoPunti;
        this.codFisc = codFisc;
    }

    public int getId() {
        return this.numTessera;
    }

    public int getSaldoPunti() {
        return this.saldoPunti;
    }

    public void setSaldoPunti(int saldoPunti) {
        this.saldoPunti = saldoPunti;
    }

    public String getCodFisc() {
        return this.codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card)) {
            return false;
        }
        Card card = (Card) o;
        return numTessera == card.numTessera && saldoPunti == card.saldoPunti && Objects.equals(codFisc, card.codFisc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numTessera, saldoPunti, codFisc);
    }

    @Override
    public String toString() {
        return "{" +
                " numTessera='" + getId() + "'" +
                ", saldoPunti='" + getSaldoPunti() + "'" +
                ", codFisc='" + getCodFisc() + "'" +
                "}";
    }

}
