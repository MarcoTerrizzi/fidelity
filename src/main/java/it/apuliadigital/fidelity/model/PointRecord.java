package it.apuliadigital.fidelity.model;

import java.time.LocalDateTime;

import it.apuliadigital.fidelity.sevice.Interface.IPointRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class PointRecord implements IPointRecord {
    static private int count=1;
    @Id
    private int id;
    private LocalDateTime purDate;
    private int numCard;
    private int orderCode;
    private int money;
    private int valPoint;

    public PointRecord() {
    }

    public PointRecord(LocalDateTime purDate, int numCard, int orderCode, int money,
            int valPoint) {
        this.id = count;
        this.purDate = purDate;
        this.numCard = numCard;
        this.orderCode = orderCode;
        this.money = money;
        this.valPoint = valPoint;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getpurDate() {
        return this.purDate;
    }

    public void setpurDate(LocalDateTime purDate) {
        this.purDate = purDate;
    }

    public int getNumCard() {
        return this.numCard;
    }

    public void setNumCard(int numCard) {
        this.numCard = numCard;
    }

    public int getorderCode() {
        return this.orderCode;
    }

    public void setorderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getmoney() {
        return this.money;
    }

    public void setmoney(int money) {
        this.money = money;
    }

    public int getValPoint() {
        return this.valPoint;
    }

    public void setValPoint(int valPoint) {
        this.valPoint = valPoint;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", purDate='" + getpurDate() + "'" +
                ", numCard='" + getNumCard() + "'" +
                ", orderCode='" + getorderCode() + "'" +
                ", money='" + getmoney() + "'" +
                ", valPoint='" + getValPoint() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PointRecord)) {
            return false;
        }
        PointRecord pointRecord = (PointRecord) o;
        return id == pointRecord.id && Objects.equals(purDate, pointRecord.purDate)
                && numCard == pointRecord.numCard && orderCode == pointRecord.orderCode
                && money == pointRecord.money && valPoint == pointRecord.valPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, purDate, numCard, orderCode, money, valPoint);
    }

}
