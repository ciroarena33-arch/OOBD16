package gui.spesa;

import javax.swing.*;

import gui.gruppo.DettagliGruppoGUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.EventQueue;

public class StoricoSpeseGUI extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StoricoSpeseGUI frame = new StoricoSpeseGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public StoricoSpeseGUI() {
        super("Storico spese");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(850, 500));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Storico spese", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        String[] colonne = {
                "Nome spesa",
                "Data",
                "Importo",
                "Valuta",
                "Pagata da"
        };

        String[][] dati = {
                {"Cena", "10/06/2026", "45.00", "Euro", "Davide"},
                {"Taxi", "11/06/2026", "20.00", "Dollaro Americano", "Marco"},
                {"Libro", "12/06/2026", "18.00", "Sterlina", "Luca"}
        };

        JTable tabellaSpese = new JTable(dati, colonne);
        JScrollPane scrollPane = new JScrollPane(tabellaSpese);

        JButton tornaGruppo = new JButton("TORNA AL GRUPPO");

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());
        panelBottoni.add(tornaGruppo);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBottoni, BorderLayout.SOUTH);

        panelPrincipale.add(panel);

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DettagliGruppoGUI.main(null);
            }
        });

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}