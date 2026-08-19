package control;

import gui.partecipanti.VisualizzaPartecipantiGUI;
import jdbc.JDBCGruppoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCUtenteDAO;
import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;
import gui.partecipanti.InfoPartecipanteGUI;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.partecipanti.AggiungiPartecipanteGruppoGUI;

public class PartecipantiController {

    private GruppoController gruppoController;
    private Utente utenteLoggato;
    private Gruppo gruppoSelezionato;
    private Utente partecipanteSelezionato;

    private JDBCUtenteDAO utenteDAO;
    private JDBCGruppoDAO gruppoDAO;
    private JDBCPartecipazioneGruppoDAO partecipazioneGruppoDAO;

    private JFrame finestraAttiva;
    private VisualizzaPartecipantiGUI visualizzaPartecipantiGUI;

    public PartecipantiController(GruppoController gruppoController) {
        this.gruppoController = gruppoController;
        this.utenteDAO = JDBCUtenteDAO.getSelf();
        this.gruppoDAO = JDBCGruppoDAO.getSelf();
        this.partecipazioneGruppoDAO = JDBCPartecipazioneGruppoDAO.getSelf();

        this.utenteLoggato = gruppoController.getUtenteLoggato();
        this.gruppoSelezionato = gruppoController.getGruppoSelezionato();

        ArrayList<PartecipazioneGruppo> partecipanti = partecipazioneGruppoDAO.cercaPartecipazioniByGruppoId(gruppoSelezionato);
        for (PartecipazioneGruppo p : partecipanti) {
            if (!p.getUtente().getEmailIstituzionale().equals(utenteLoggato.getEmailIstituzionale())) {
                gruppoSelezionato.addComponente(p);
            }
        }
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public Utente getUtenteLoggato() {
        return utenteLoggato;
    }

    public Gruppo getGruppoSelezionato() {
        return gruppoSelezionato;
    }

    public Utente getPartecipanteSelezionato() {
        return partecipanteSelezionato;
    }

    public void avvia() {
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        mostraFinestra(visualizzaPartecipantiGUI);
    }

    public void caricaPartecipanti() {
        DefaultListModel<Object> model = new DefaultListModel<>();
        for (PartecipazioneGruppo g : gruppoSelezionato.getComponenti()) {
            Utente u = g.getUtente();
            if (!utenteLoggato.getEmailIstituzionale().equals(u.getEmailIstituzionale()) && g.isInvitoAccettato()) {
                model.addElement(g.getUtente());
            }
        }
        if (visualizzaPartecipantiGUI != null) {
            visualizzaPartecipantiGUI.aggiornaJList(model);
        }
    }

    public void btn_visualizzaPartecipanti_vediDettagli(Object u) {
        partecipanteSelezionato = (Utente) u;
        boolean puoRendereProprietario = utenteLoggato.getEmailIstituzionale().equals(gruppoSelezionato.getProprietario().getEmailIstituzionale());
        mostraFinestra(new InfoPartecipanteGUI(
                this,
                partecipanteSelezionato.getNome(),
                partecipanteSelezionato.getCognome(),
                partecipanteSelezionato.getEmailIstituzionale(),
                partecipanteSelezionato.getTelefono(),
                puoRendereProprietario
        ));
    }

    public void btn_visualizzaPartecipanti_aggiungiPartecipante() {
        mostraFinestra(new AggiungiPartecipanteGruppoGUI(this));
    }

    public void btn_visualizzaPartecipanti_tornaGruppo() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }
        gruppoController.tornaDettagliGruppoDaPartecipanti();
    }

    public void btn_infoPartecipante_indietro() {
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        mostraFinestra(visualizzaPartecipantiGUI);
    }

    public void btn_infoPartecipante_rendiProprietario() {
        try {
            if (JOptionPane.showConfirmDialog(null, "Confermi di voler trasferire il ruolo di proprietario a " + partecipanteSelezionato + "?") == JOptionPane.YES_OPTION) {
                gruppoSelezionato.setProprietario(partecipanteSelezionato);
                gruppoDAO.aggiornaGruppo(gruppoSelezionato);

                visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
                caricaPartecipanti();
                mostraFinestra(visualizzaPartecipantiGUI);
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_aggiungiPartecipante_aggiungi(String email) {
        try {
            boolean invito = true;
            PartecipazioneGruppo partecipazione = null;
            for (PartecipazioneGruppo partecipante : gruppoSelezionato.getComponenti()) {
                if (partecipante.getUtente().getEmailIstituzionale().equals(email) || utenteLoggato.getEmailIstituzionale().equals(email)) {
                    invito = partecipante.isInvitoAccettato();
                    partecipazione = partecipante;
                    if (invito) {
                        throw new RuntimeException("L'utente già appartiene al gruppo");
                    }
                    break;
                }
            }
            if (!invito && partecipazione != null) {
                partecipazione.setData(LocalDate.now());
                partecipazioneGruppoDAO.aggiornaPartecipazione(partecipazione);
            } else {
                Utente u = utenteDAO.cercaUtentePerEmail(email);
                PartecipazioneGruppo p = new PartecipazioneGruppo(LocalDate.now(), u, gruppoSelezionato);
                partecipazioneGruppoDAO.nuovaPartecipazione(p);
                gruppoSelezionato.addComponente(p);
            }

            JOptionPane.showMessageDialog(null, "Utente invitato nel gruppo");
            visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
            caricaPartecipanti();
            mostraFinestra(visualizzaPartecipantiGUI);

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_aggiungiPartecipante_annulla() {
        visualizzaPartecipantiGUI = new VisualizzaPartecipantiGUI(this);
        caricaPartecipanti();
        mostraFinestra(visualizzaPartecipantiGUI);
    }
}