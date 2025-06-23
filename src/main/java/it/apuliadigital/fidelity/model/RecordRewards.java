package it.apuliadigital.fidelity.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class RecordRewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Il numero della tessera non può essere nullo")
    private Long numTessera;

    @NotNull(message = "L'ID del premio riscattato non può essere nullo")
    private Long premioRiscattato;

    @NotBlank(message = "Il nome del premio riscattato non può essere vuoto")
    @Size(max = 100, message = "Il nome del premio può contenere al massimo 100 caratteri")
    private String nomePremioRiscattato;

    @PastOrPresent(message = "La data di acquisizione non può essere futura")
    private LocalDateTime dataAcquisizione;

    public RecordRewards() {
        this.dataAcquisizione = LocalDateTime.now();
    }

    public RecordRewards(Long numTessera, Long premioRiscattato, String nomePremioRiscattato) {
        this.numTessera = numTessera;
        this.premioRiscattato = premioRiscattato;
        this.nomePremioRiscattato = nomePremioRiscattato;
        this.dataAcquisizione = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumTessera() {
        return numTessera;
    }

    public void setNumTessera(Long numTessera) {
        this.numTessera = numTessera;
    }

    public Long getPremioRiscattato() {
        return premioRiscattato;
    }

    public void setPremioRiscattato(Long premioRiscattato) {
        this.premioRiscattato = premioRiscattato;
    }

    public String getNomePremioRiscattato() {
        return nomePremioRiscattato;
    }

    public void setNomePremioRiscattato(String nomePremioRiscattato) {
        this.nomePremioRiscattato = nomePremioRiscattato;
    }

    public LocalDateTime getDataAcquisizione() {
        return dataAcquisizione;
    }

    public void setDataAcquisizione(LocalDateTime dataAcquisizione) {
        this.dataAcquisizione = dataAcquisizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordRewards)) return false;
        RecordRewards that = (RecordRewards) o;
        return Objects.equals(id, that.id)
                && Objects.equals(numTessera, that.numTessera)
                && Objects.equals(premioRiscattato, that.premioRiscattato)
                && Objects.equals(nomePremioRiscattato, that.nomePremioRiscattato)
                && Objects.equals(dataAcquisizione, that.dataAcquisizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numTessera, premioRiscattato, nomePremioRiscattato, dataAcquisizione);
    }

    @Override
    public String toString() {
        return "RecordRewards{" +
                "id=" + id +
                ", numTessera=" + numTessera +
                ", premioRiscattato=" + premioRiscattato +
                ", nomePremioRiscattato='" + nomePremioRiscattato + '\'' +
                ", dataAcquisizione=" + dataAcquisizione +
                '}';
    }
}
