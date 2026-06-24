
package gui.gruppo;

import javax.swing.*;

import gui.spesa.InserisciSpesaGUI;
import gui.spesa.StoricoSpeseGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.EventQueue;
import gui.partecipanti.VisualizzaPartecipantiGUI;
import gui.partecipanti.AggiungiPartecipanteGruppoGUI;

public class DettagliGruppoGUI extends JFrame {

    public static String nomeGruppoSelezionato = "Nessun gruppo selezionato";

    public DettagliGruppoGUI() {
        super("Dettaglio gruppo");

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
        JButton visualizzaPartecipanti = new JButton("VISUALIZZA PARTECIPANTI");
        JButton infoGruppo = new JButton("INFO GRUPPO");
        JButton tornaGruppi = new JButton("TORNA AI GRUPPI");

        panel.add(logo);
        panel.add(titoloGruppo);
        panel.add(numeroPartecipanti);
        panel.add(inserisciSpesa);
        panel.add(storicoSpese);
        panel.add(visualizzaPartecipanti);
        panel.add(infoGruppo);
        panel.add(tornaGruppi);

        panelPrincipale.add(panel);

        inserisciSpesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InserisciSpesaGUI.main(null);
            }
        });

        storicoSpese.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StoricoSpeseGUI.main(null);
            }
        });

        visualizzaPartecipanti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaPartecipantiGUI.main(null);
            }
        });

        infoGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AggiungiPartecipanteGruppoGUI.main(null);
            }
        });

        tornaGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                IMieiGruppiGUI.main(null);
            }
        });

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}