package it.apuliadigital.fidelity.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Card {
    private static int count = 1;

    @Id
    private int numCard;
    private int balancePoint;
    private String codFisc;

    public Card() {
    }

    public Card(int saldoPoint, String codFisc) {
        this.numCard = count++;
        this.balancePoint = saldoPoint;
        this.codFisc = codFisc;
    }

    public int getId() {
        return this.numCard;
    }

    public int getBalancePoint() {
        return this.balancePoint;
    }

    public void setBalancePoint(int saldoPoint) {
        this.balancePoint = saldoPoint;
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
        return numCard == card.numCard && balancePoint == card.balancePoint && Objects.equals(codFisc, card.codFisc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCard, balancePoint, codFisc);
    }

    @Override
    public String toString() {
        return "{" +
                " numCard='" + getId() + "'" +
                ", saldoPoint='" + getBalancePoint() + "'" +
                ", codFisc='" + getCodFisc() + "'" +
                "}";
    }

}
