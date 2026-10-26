package control;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import gui.spesa.InserisciSpesaGUI;
import gui.spesa.StoricoSpeseGUI;

import jdbc.JDBCDebitoDAO;
import jdbc.JDBCPartecipazioneGruppoDAO;
import jdbc.JDBCSpesaDAO;
import jdbc.JDBCValutaDAO;

import model.Debito;
import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Spesa;
import model.Utente;
import model.Valuta;

public class SpesaController {

    private Utente utenteLoggato;
    private Gruppo gruppoSelezionato;

    private GruppoController gruppoController;
    private JFrame finestraAttiva;

    private InserisciSpesaGUI inserisciSpesaGUI;
    private StoricoSpeseGUI storicoSpeseGUI;

    private JDBCSpesaDAO spesaDAO;
    private JDBCValutaDAO valutaDAO;
    private JDBCDebitoDAO debitoDAO;
    private JDBCPartecipazioneGruppoDAO partecipazioneDAO;

    public SpesaController(GruppoController gruppoController) {
        try {
            this.gruppoController = gruppoController;

            this.spesaDAO = new JDBCSpesaDAO();
            this.valutaDAO = new JDBCValutaDAO();
            this.debitoDAO = new JDBCDebitoDAO();
            this.partecipazioneDAO = new JDBCPartecipazioneGruppoDAO();

            this.utenteLoggato = gruppoController.getUtenteLoggato();
            this.gruppoSelezionato = gruppoController.getGruppoSelezionato();

            ArrayList<Spesa> spese = spesaDAO.cercaSpesaByGruppo(
                    gruppoSelezionato, utenteLoggato
            );

            for (Spesa s : spese) {
                gruppoSelezionato.addSpesa(s);
            }

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void mostraFinestra(JFrame nuovaFinestra) {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }

        finestraAttiva = nuovaFinestra;
        finestraAttiva.setVisible(true);
    }

    public void impostaStorico() {
        try {
            if (storicoSpeseGUI == null) return;

            DefaultTableModel model = storicoSpeseGUI.getTableModel();
            model.setRowCount(0);

            for (Spesa spesa : gruppoSelezionato.getSpese()) {
                Object[] riga = new Object[]{
                        spesa,
                        spesa.getData(),
                        String.valueOf(spesa.getImporto()),
                        spesa.getValuta() != null
                                ? spesa.getValuta().getNome()
                                : "EUR",
                        spesa.isComune() ? "Comune" : "Personale",
                        spesa.getUtenteEffettuante().toString()
                };

                model.addRow(riga);
            }

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void caricaValute() {
        if (inserisciSpesaGUI == null) return;

        ArrayList<Valuta> valute = valutaDAO.tutteLeValute();
        String[] nomiValute;

        if (valute != null && !valute.isEmpty()) {
            nomiValute = new String[valute.size()];

            for (int i = 0; i < valute.size(); i++) {
                nomiValute[i] = valute.get(i).getNome().toUpperCase();
            }
        } else {
            nomiValute = new String[]{"EUR"};
        }

        inserisciSpesaGUI.aggiornaValute(nomiValute);
    }

    public void caricaPartecipanti() {
        if (inserisciSpesaGUI == null) return;

        ArrayList<PartecipazioneGruppo> partecipazioni =
                partecipazioneDAO.cercaPartecipazioniByGruppoId(gruppoSelezionato);

        ArrayList<Utente> listaMembri = new ArrayList<>();
        boolean presente = false;

        for (PartecipazioneGruppo p : partecipazioni) {
            if (p.isInvitoAccettato()) {
                Utente utente = p.getUtente();
                listaMembri.add(utente);

                if (utente.getEmailIstituzionale()
                        .equalsIgnoreCase(utenteLoggato.getEmailIstituzionale())) {
                    presente = true;
                }
            }
        }

        if (!presente) {
            listaMembri.add(utenteLoggato);
        }

        inserisciSpesaGUI.aggiornaPartecipanti(
                listaMembri.toArray(new Utente[0])
        );
    }

    public void avviaInserisciSpesa() {
        inserisciSpesaGUI = new InserisciSpesaGUI(this);

        caricaValute();
        caricaPartecipanti();

        mostraFinestra(inserisciSpesaGUI);
    }

    public void avviaStoricoSpese() {
        storicoSpeseGUI = new StoricoSpeseGUI(this);

        impostaStorico();

        mostraFinestra(storicoSpeseGUI);
    }

    public void btn_inserisciSpesa_registraSpesa(
            String nome,
            String descrizione,
            String importoStr,
            String valutaNome,
            LocalDate data,
            String tipo) {

        try {
            double importo = Double.parseDouble(importoStr);
            boolean isComune = "COMUNE".equalsIgnoreCase(tipo);
            Valuta valuta = valutaDAO.cercaValuta(valutaNome);
            if (valuta == null) {
                valuta = new Valuta("EUR", 1.0);
            }

            Spesa spesa = new Spesa(
                    nome,
                    descrizione,
                    data,
                    importo,
                    isComune,
                    valuta,
                    gruppoSelezionato,
                    utenteLoggato
            );

            spesaDAO.nuovaSpesa(spesa);
            gruppoSelezionato.addSpesa(spesa);

            // Debiti per spese comuni
            if (isComune && inserisciSpesaGUI != null) {

                ArrayList<Utente> selezionati = inserisciSpesaGUI.getPartecipantiSelezionati();
                if (!selezionati.isEmpty()) {
                    double quota = importo / selezionati.size();
                    for (Utente utente : selezionati) {

                        if (!utente.getEmailIstituzionale().equalsIgnoreCase(utenteLoggato.getEmailIstituzionale())) {
                            Debito debito = new Debito(spesa,utente,quota,false);
                            debitoDAO.nuovoDebito(debito);
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(
                    null, "Spesa registrata con successo!"
            );

            if (finestraAttiva != null) {
                finestraAttiva.dispose();
            }

            gruppoController.tornaDettagliGruppoDaSpesa();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "Importo inserito non valido."
            );

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void btn_inserisciSpesa_tornaGruppo() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }

        gruppoController.tornaDettagliGruppoDaSpesa();
    }

    public void btn_storicoSpese_tornaGruppo() {
        if (finestraAttiva != null) {
            finestraAttiva.dispose();
        }

        gruppoController.tornaDettagliGruppoDaSpesa();
    }
}