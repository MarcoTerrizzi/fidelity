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
    private LocalDateTime dataAcquisto;
    private int numTessera;
    private int codiceOrdine;
    private int money;
    private int valPunti;

    public PointRecord() {
    }

    public PointRecord(LocalDateTime dataAcquisto, int numTessera, int codiceOrdine, int money,
            int valPunti) {
        this.id = count;
        this.dataAcquisto = dataAcquisto;
        this.numTessera = numTessera;
        this.codiceOrdine = codiceOrdine;
        this.money = money;
        this.valPunti = valPunti;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataAcquisto() {
        return this.dataAcquisto;
    }

    public void setDataAcquisto(LocalDateTime dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public int getNumTessera() {
        return this.numTessera;
    }

    public void setNumTessera(int numTessera) {
        this.numTessera = numTessera;
    }

    public int getCodiceOrdine() {
        return this.codiceOrdine;
    }

    public void setCodiceOrdine(int codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public int getmoney() {
        return this.money;
    }

    public void setmoney(int money) {
        this.money = money;
    }

    public int getValPunti() {
        return this.valPunti;
    }

    public void setValPunti(int valPunti) {
        this.valPunti = valPunti;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", dataAcquisto='" + getDataAcquisto() + "'" +
                ", numTessera='" + getNumTessera() + "'" +
                ", codiceOrdine='" + getCodiceOrdine() + "'" +
                ", money='" + getmoney() + "'" +
                ", valPunti='" + getValPunti() + "'" +
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
        return id == pointRecord.id && Objects.equals(dataAcquisto, pointRecord.dataAcquisto)
                && numTessera == pointRecord.numTessera && codiceOrdine == pointRecord.codiceOrdine
                && money == pointRecord.money && valPunti == pointRecord.valPunti;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataAcquisto, numTessera, codiceOrdine, money, valPunti);
    }

}
