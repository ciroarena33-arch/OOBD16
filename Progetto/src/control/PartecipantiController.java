package control;

import gui.partecipanti.VisualizzaPartecipantiGUI;
import gui.partecipanti.InfoPartecipante;
import gui.partecipanti.AggiungiPartecipanteGruppoGUI;

public class PartecipantiController {

    private GruppoController gruppoController;

    private VisualizzaPartecipantiGUI visualizzaPartecipantiGUI;
    private InfoPartecipante infoPartecipanteGUI;
    private AggiungiPartecipanteGruppoGUI aggiungiPartecipanteGUI;

    public PartecipantiController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
    }

    public void avvia() {
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        visualizzaPartecipantiGUI.setVisible(true);
    }

    public void btn_visualizzaPartecipanti_vediDettagli(String nome, String cognome, String email, String telefono) {
        if (visualizzaPartecipantiGUI != null) {
            visualizzaPartecipantiGUI.dispose();
        }

        infoPartecipanteGUI = new InfoPartecipante(this, nome, cognome, email, telefono);
        infoPartecipanteGUI.setVisible(true);
    }

    public void btn_visualizzaPartecipanti_aggiungiPartecipante() {
        if (visualizzaPartecipantiGUI != null) {
            visualizzaPartecipantiGUI.dispose();
        }

        aggiungiPartecipanteGUI = new AggiungiPartecipanteGruppoGUI(this);
        aggiungiPartecipanteGUI.setVisible(true);
    }

    public void btn_visualizzaPartecipanti_tornaGruppo() {
        if (visualizzaPartecipantiGUI != null) {
            visualizzaPartecipantiGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaPartecipanti();
    }

    public void btn_infoPartecipante_indietro() {
        if (infoPartecipanteGUI != null) {
            infoPartecipanteGUI.dispose();
        }

        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        visualizzaPartecipantiGUI.setVisible(true);
    }

    public void btn_infoPartecipante_rendiProprietario(String email) {
        System.out.println("Partecipante reso proprietario: " + email);
    }

    public void btn_aggiungiPartecipante_aggiungi(String email) {
        System.out.println("Partecipante aggiunto: " + email);

        if (aggiungiPartecipanteGUI != null) {
            aggiungiPartecipanteGUI.dispose();
        }

        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        visualizzaPartecipantiGUI.setVisible(true);
    }

    public void btn_aggiungiPartecipante_annulla() {
        if (aggiungiPartecipanteGUI != null) {
            aggiungiPartecipanteGUI.dispose();
        }

        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        visualizzaPartecipantiGUI.setVisible(true);
    }
}