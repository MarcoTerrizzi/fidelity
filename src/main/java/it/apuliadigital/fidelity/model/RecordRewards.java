package it.apuliadigital.fidelity.model;

import java.time.LocalDateTime;
import java.util.Objects;

import it.apuliadigital.fidelity.sevice.Interface.IRecordRewards;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RecordRewards implements IRecordRewards {
    @Id
    private int id;

    private int numTessera;
    private int premioRiscattato;
    private String nomePremioRiscattato;
    private LocalDateTime dataAcquisizione;

    public RecordRewards() {
    }

    public RecordRewards(int id, int numTessera, int premioRiscattato, String nomePremioRiscattato,
            LocalDateTime dataAcquisizione) {
        this.id = id;
        this.numTessera = numTessera;
        this.premioRiscattato = premioRiscattato;
        this.nomePremioRiscattato = nomePremioRiscattato;
        this.dataAcquisizione = dataAcquisizione;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTessera() {
        return this.numTessera;
    }

    public void setNumTessera(int numTessera) {
        this.numTessera = numTessera;
    }

    public int getPremioRiscattato() {
        return this.premioRiscattato;
    }

    public void setPremioRiscattato(int premioRiscattato) {
        this.premioRiscattato = premioRiscattato;
    }

    public String getNomePremioRiscattato() {
        return this.nomePremioRiscattato;
    }

    public void setNomePremioRiscattato(String nomePremioRiscattato) {
        this.nomePremioRiscattato = nomePremioRiscattato;
    }

    public LocalDateTime getDataAcquisizione() {
        return this.dataAcquisizione;
    }

    public void setDataAcquisizione(LocalDateTime dataAcquisizione) {
        this.dataAcquisizione = dataAcquisizione;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RecordRewards)) {
            return false;
        }
        RecordRewards recordRewards = (RecordRewards) o;
        return id == recordRewards.id && numTessera == recordRewards.numTessera
                && premioRiscattato == recordRewards.premioRiscattato
                && Objects.equals(nomePremioRiscattato, recordRewards.nomePremioRiscattato)
                && Objects.equals(dataAcquisizione, recordRewards.dataAcquisizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numTessera, premioRiscattato, nomePremioRiscattato, dataAcquisizione);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", numTessera='" + numTessera + "'" +
                ", premioRiscattato='" + premioRiscattato + "'" +
                ", nomePremioRiscattato='" + nomePremioRiscattato + "'" +
                ", dataAcquisizione='" + dataAcquisizione + "'" +
                "}";
    }

}
