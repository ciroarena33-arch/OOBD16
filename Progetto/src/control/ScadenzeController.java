package control;

import gui.scadenze.ScadenzeGUI;
import gui.scadenze.AggiungiScadenzaGUI;
import gui.scadenze.DettaglioScadenzaGUI;

public class ScadenzeController {

    private GruppoController gruppoController;

    private ScadenzeGUI scadenzeGUI;
    private AggiungiScadenzaGUI aggiungiScadenzaGUI;
    private DettaglioScadenzaGUI dettaglioScadenzaGUI;

    public ScadenzeController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
    }

    public void avvia() {
        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }

    public void btn_scadenze_aggiungi() {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }

        aggiungiScadenzaGUI = new AggiungiScadenzaGUI(this);
        aggiungiScadenzaGUI.setVisible(true);
    }

    public void btn_scadenze_modifica(String nome, String data, String importo) {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }

        dettaglioScadenzaGUI = new DettaglioScadenzaGUI(this, nome, data, importo);
        dettaglioScadenzaGUI.setVisible(true);
    }

    public void btn_scadenze_indietro() {
        if (scadenzeGUI != null) {
            scadenzeGUI.dispose();
        }

        gruppoController.tornaDettagliGruppoDaScadenze();
    }

    public void btn_aggiungiScadenza_aggiungi(String nome, String data, String importo) {
        System.out.println("Scadenza aggiunta: " + nome + " - " + data + " - " + importo);

        if (aggiungiScadenzaGUI != null) {
            aggiungiScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }

    public void btn_aggiungiScadenza_indietro() {
        if (aggiungiScadenzaGUI != null) {
            aggiungiScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }

    public void btn_dettaglioScadenza_salva(String nome, String data, String importo) {
        System.out.println("Scadenza salvata: " + nome + " - " + data + " - " + importo);

        if (dettaglioScadenzaGUI != null) {
            dettaglioScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }

    public void btn_dettaglioScadenza_cancella() {
        System.out.println("Scadenza cancellata");

        if (dettaglioScadenzaGUI != null) {
            dettaglioScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }

    public void btn_dettaglioScadenza_indietro() {
        if (dettaglioScadenzaGUI != null) {
            dettaglioScadenzaGUI.dispose();
        }

        scadenzeGUI = new ScadenzeGUI(this);
        scadenzeGUI.setVisible(true);
    }
}