package it.apuliadigital.fidelity.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il nome del premio non può essere vuoto")
    @Size(max = 100, message = "Il nome può contenere massimo 100 caratteri")
    private String nome;

    @Min(value = 1, message = "I punti necessari devono essere almeno 1")
    private int puntiNecessari;

    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(max = 255, message = "La descrizione può contenere massimo 255 caratteri")
    private String descrizione;

    public Rewards() {
    }

    public Rewards(String nome, int puntiNecessari, String descrizione) {
        this.nome = nome;
        this.puntiNecessari = puntiNecessari;
        this.descrizione = descrizione;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiNecessari() {
        return puntiNecessari;
    }

    public void setPuntiNecessari(int puntiNecessari) {
        this.puntiNecessari = puntiNecessari;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rewards rewards = (Rewards) o;
        return puntiNecessari == rewards.puntiNecessari && Objects.equals(id, rewards.id)
                && Objects.equals(nome, rewards.nome) && Objects.equals(descrizione, rewards.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, puntiNecessari, descrizione);
    }

    @Override
    public String toString() {
        return "Rewards{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", puntiNecessari=" + puntiNecessari +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
