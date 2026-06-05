
package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DettagliGruppoGUI {

    public static String nomeGruppoSelezionato = "Nessun gruppo selezionato";

    public static void main(String[] args) {

        JFrame frame = new JFrame("Dettaglio gruppo");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 560));
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel titoloGruppo = new JLabel("Gruppo: " + nomeGruppoSelezionato, SwingConstants.CENTER);
        titoloGruppo.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel numeroPartecipanti = new JLabel("Numero partecipanti: 3", SwingConstants.CENTER);

        JButton inserisciSpesa = new JButton("INSERISCI SPESA");
        JButton storicoSpese = new JButton("STORICO SPESE");
        JButton reportSaldi = new JButton("GESTIONE SPESA");
        JButton visualizzaPartecipanti = new JButton("VISUALIZZA PARTECIPANTI");
        JButton aggiungiPartecipante = new JButton("AGGIUNGI PARTECIPANTE");
        JButton tornaGruppi = new JButton("TORNA AI GRUPPI");

        panel.add(logo);
        panel.add(titoloGruppo);
        panel.add(numeroPartecipanti);
        panel.add(inserisciSpesa);
        panel.add(storicoSpese);
        panel.add(reportSaldi);
        panel.add(visualizzaPartecipanti);
        panel.add(aggiungiPartecipante);
        panel.add(tornaGruppi);

        panelPrincipale.add(panel);

        inserisciSpesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                InserisciSpesaGUI.main(null);
            }
        });

        storicoSpese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                StoricoSpeseGUI.main(null);
            }
        });

        reportSaldi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                GestioneSpeseGUI.main(null);
            }
        });

        visualizzaPartecipanti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaPartecipantiGUI.main(null);
            }
        });

        aggiungiPartecipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiPartecipanteGruppoGUI.main(null);
            }
        });

        tornaGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                I_Miei_GruppiGUI.main(null);
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}