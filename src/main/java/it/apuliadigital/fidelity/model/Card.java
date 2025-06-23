package it.apuliadigital.fidelity.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numCard;

    @Min(value = 0, message = "Il saldo punti deve essere maggiore o uguale a zero")
    private int balancePoint;

    @NotBlank(message = "Il codice fiscale non può essere vuoto")
    private String codFisc;

    public Card() {
    }

    public Card(int balancePoint, String codFisc) {
        this.balancePoint = balancePoint;
        this.codFisc = codFisc;
    }

    // Getters and Setters

    public Long getNumCard() {
        return numCard;
    }

    public void setNumCard(Long numCard) {
        this.numCard = numCard;
    }

    public int getBalancePoint() {
        return this.balancePoint;
    }

    public void setBalancePoint(int balancePoint) {
        this.balancePoint = balancePoint;
    }

    public String getCodFisc() {
        return this.codFisc;
    }

    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return balancePoint == card.balancePoint &&
                Objects.equals(numCard, card.numCard) &&
                Objects.equals(codFisc, card.codFisc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numCard, balancePoint, codFisc);
    }

    @Override
    public String toString() {
        return "Card{" +
                "numCard=" + numCard +
                ", balancePoint=" + balancePoint +
                ", codFisc='" + codFisc + '\'' +
                '}';
    }
}
