package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestioneSpeseGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Gestione debiti");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 500));
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Gestione debiti", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        String[] colonne = {
                "Debitore",
                "Importo"
        };

        String[][] dati = {
                {"Marco", "10.00 €"},
                {"Luca", "10.00 €"},
                {"Davide", "5.00 €"}
        };

        JTable tabellaDebiti = new JTable(dati, colonne);
        JScrollPane scrollPane = new JScrollPane(tabellaDebiti);

        JButton segnaSaldato = new JButton("SEGNA COME SALDATO");
        JButton tornaGruppo = new JButton("TORNA AL GRUPPO");

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(titolo);
        panel.add(scrollPane);
        panel.add(segnaSaldato);
        panel.add(tornaGruppo);
        panel.add(messaggio);

        panelPrincipale.add(panel);

        segnaSaldato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int riga = tabellaDebiti.getSelectedRow();

                if (riga == -1) {
                    messaggio.setText("Seleziona prima un debito");
                } else {
                    messaggio.setText("Debito segnato come saldato");
                }
            }
        });

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DettaglioGruppoGUI.main(null);
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}