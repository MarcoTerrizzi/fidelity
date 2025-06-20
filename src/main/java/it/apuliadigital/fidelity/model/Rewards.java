package it.apuliadigital.fidelity.model;

import java.util.Objects;

import jakarta.persistence.Id;

public class Rewards  {
    static private int count = 1;
    @Id
    private int id;
    private String nome;
    private int puntiNecessari;
    private String descrizione;

    public Rewards() {
    }

    public Rewards(int id, String nome, int puntiNecessari, String descrizione) {
        this.id = count++;
        this.nome = nome;
        this.puntiNecessari = puntiNecessari;
        this.descrizione = descrizione;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPuntiNecessari() {
        return this.puntiNecessari;
    }

    public void setPuntiNecessari(int puntiNecessari) {
        this.puntiNecessari = puntiNecessari;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rewards)) {
            return false;
        }
        Rewards rewards = (Rewards) o;
        return id == rewards.id && Objects.equals(nome, rewards.nome) && puntiNecessari == rewards.puntiNecessari
                && Objects.equals(descrizione, rewards.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, puntiNecessari, descrizione);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", nome='" + nome + "'" +
                ", puntiNecessari='" + puntiNecessari + "'" +
                ", descrizione='" + descrizione + "'" +
                "}";
    }

}
