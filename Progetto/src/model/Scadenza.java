package model;

import java.time.LocalDate;
import java.util.Objects;

public class Scadenza {

    private int id;
    private String nome;
    private LocalDate dataScadenza;
    private double importo;
    private Gruppo gruppo;

    public Scadenza(String nome, LocalDate dataScadenza, double importo, Gruppo gruppo) {
        this.nome = nome;
        this.dataScadenza = dataScadenza;
        this.importo = importo;
        this.gruppo = gruppo;
    }

    public Scadenza(int id, String nome, LocalDate dataScadenza, double importo, Gruppo gruppo) {
        this.id = id;
        this.nome = nome;
        this.dataScadenza = dataScadenza;
        this.importo = importo;
        this.gruppo = gruppo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getImporto() {
        return importo;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Scadenza other = (Scadenza) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
