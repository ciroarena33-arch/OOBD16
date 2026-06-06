package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AggiungiPartecipanteGruppoGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Aggiungi partecipante");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        JLabel titolo = new JLabel("Aggiungi partecipante", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel labelEmail = new JLabel("Email utente:");
        JTextField fieldEmail = new JTextField();

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton aggiungi = new JButton("AGGIUNGI");
        JButton annulla = new JButton("ANNULLA");

        panelBottoni.add(aggiungi);
        panelBottoni.add(annulla);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);

        panel.add(titolo);
        panel.add(labelEmail);
        panel.add(fieldEmail);
        panel.add(panelBottoni);
        panel.add(messaggio);

        aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = fieldEmail.getText();

                if (email.isEmpty()) {
                    messaggio.setText("Inserisci una email");
                } else {
                    messaggio.setText("Partecipante aggiunto: " + email);
                }
            }
        });

        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setContentPane(panel);
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // chiudo solo la finestra
        frame.setVisible(true);
    }
}