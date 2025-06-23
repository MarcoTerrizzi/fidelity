package it.apuliadigital.fidelity.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PointRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime purDate;
    private Long numCard;
    private Long orderCode;
    private int money;
    private int valPoint;

    public PointRecord() {
    }

    public PointRecord(LocalDateTime purDate, Long numCard, Long orderCode, int money, int valPoint) {
        this.purDate = purDate;
        this.numCard = numCard;
        this.orderCode = orderCode;
        this.money = money;
        this.valPoint = valPoint;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPurDate() {
        return purDate;
    }

    public void setPurDate(LocalDateTime purDate) {
        this.purDate = purDate;
    }

    public Long getNumCard() {
        return numCard;
    }

    public void setNumCard(Long numCard) {
        this.numCard = numCard;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getValPoint() {
        return valPoint;
    }

    public void setValPoint(int valPoint) {
        this.valPoint = valPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PointRecord that = (PointRecord) o;
        return money == that.money && valPoint == that.valPoint && Objects.equals(id, that.id)
                && Objects.equals(purDate, that.purDate) && Objects.equals(numCard, that.numCard)
                && Objects.equals(orderCode, that.orderCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purDate, numCard, orderCode, money, valPoint);
    }

    @Override
    public String toString() {
        return "PointRecord{" +
                "id=" + id +
                ", purDate=" + purDate +
                ", numCard=" + numCard +
                ", orderCode=" + orderCode +
                ", money=" + money +
                ", valPoint=" + valPoint +
                '}';
    }
}
