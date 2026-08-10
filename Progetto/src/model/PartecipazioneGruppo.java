package model;

import java.time.LocalDate;
import java.util.Objects;

public class PartecipazioneGruppo {

    private int id;
    private LocalDate data;
    private Boolean invitoAccettato;
    private Utente utente;
    private Gruppo gruppo;

    public PartecipazioneGruppo(LocalDate data, Utente utente, Gruppo gruppo) {
        this.data = data;
        this.invitoAccettato = false;
        this.utente = utente;
        this.gruppo = gruppo;
    }

    public PartecipazioneGruppo(int id, LocalDate data, Boolean invitoAccettato, Utente utente, Gruppo gruppo) {
        this.id = id;
        this.data = data;
        this.invitoAccettato = invitoAccettato;
        this.utente = utente;
        this.gruppo = gruppo;
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Boolean isInvitoAccettato() {
        return invitoAccettato;
    }

    public Utente getUtente() {
        return utente;
    }

    public Gruppo getGruppo() {
        return gruppo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setInvitoAccettato(Boolean invitoAccettato) {
        this.invitoAccettato = invitoAccettato;
    }

    @Override
    public String toString() {
        return this.gruppo.getNome();
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
        PartecipazioneGruppo other = (PartecipazioneGruppo) obj;
        return id == other.id;
    }
}
