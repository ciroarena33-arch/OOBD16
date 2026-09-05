package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gruppo {

    private int id;
    private String nome;
    private ArrayList<PartecipazioneGruppo> componenti;
    private Utente proprietario;
    private LocalDate dataCreazione;
    private ArrayList<Spesa> spese;

    public Gruppo(int id, String nome, Utente proprietario, LocalDate dataCreazione) {
        this.id = id;
        this.nome = nome;
        this.proprietario = proprietario;
        this.componenti = new ArrayList<>();
        this.dataCreazione = dataCreazione;
        this.spese = new ArrayList<>();
    }

    public Gruppo(String nome, Utente proprietario, LocalDate dataCreazione) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.componenti = new ArrayList<>();
        this.dataCreazione = LocalDate.now();
        this.spese = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Utente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Utente proprietario) {
        this.proprietario = proprietario;
    }

    public ArrayList<PartecipazioneGruppo> getComponenti() {
        return componenti;
    }

    public ArrayList<Spesa> getSpese() {
        return spese;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void addComponente(PartecipazioneGruppo componente) {
        if (componenti.contains(componente)) {
            return;
        }
        componenti.add(componente);
    }

    public void removeComponente(PartecipazioneGruppo componente) {
        if (!componenti.contains(componente)) {
            return;
        }
        componenti.remove(componente);
    }

    public void addSpesa(Spesa s) {
        if (!spese.contains(s)) {
            spese.add(s);
        }
    }

    public void removeSpesa(Spesa s) {
        if (spese.contains(s)) {
            spese.remove(s);
        }
    }

    public int getNumeroSpese() {
        return spese.size();
    }

    public double getImportoTotale() {
        double totale = 0;
        for (Spesa spesa : spese) {
            totale += spesa.getImporto();
        }
        return totale;
    }
}
