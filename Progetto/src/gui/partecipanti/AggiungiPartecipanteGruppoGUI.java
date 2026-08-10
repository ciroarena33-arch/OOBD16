package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.PartecipantiController;

public class AggiungiPartecipanteGruppoGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private PartecipantiController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel labelEmail;
    private JTextField fieldEmail;
    private JButton aggiungi;
    private JButton annulla;
    private JLabel messaggio;

    public AggiungiPartecipanteGruppoGUI(PartecipantiController controller) {
        this.controller = controller;

        setTitle("Aggiungi Partecipante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 280);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        setContentPane(contentPane);

        titolo = new JLabel("Aggiungi Partecipante", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 20, 480, 36);
        contentPane.add(titolo);

        labelEmail = new JLabel("Email utente:");
        labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelEmail.setForeground(new Color(70, 85, 105));
        labelEmail.setBounds(60, 80, 340, 20);
        contentPane.add(labelEmail);

        fieldEmail = new JTextField();
        fieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldEmail.setBounds(60, 108, 360, 32);
        contentPane.add(fieldEmail);

        aggiungi = new JButton("AGGIUNGI");
        aggiungi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        aggiungi.setBackground(new Color(60, 120, 216));
        aggiungi.setForeground(Color.WHITE);
        aggiungi.setFocusPainted(false);
        aggiungi.setBounds(100, 168, 120, 36);
        aggiungi.addActionListener(e -> {
            String email = fieldEmail.getText().trim();
            if (email.isEmpty()) {
                messaggio.setText("Inserisci una email");
            } else {
                controller.btn_aggiungiPartecipante_aggiungi(email);
            }
        });
        contentPane.add(aggiungi);

        annulla = new JButton("ANNULLA");
        annulla.setFont(new Font("Segoe UI", Font.BOLD, 13));
        annulla.setBackground(new Color(110, 120, 135));
        annulla.setForeground(Color.WHITE);
        annulla.setFocusPainted(false);
        annulla.setBounds(250, 168, 120, 36);
        annulla.addActionListener(e -> controller.btn_aggiungiPartecipante_annulla());
        contentPane.add(annulla);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(60, 220, 360, 25);
        contentPane.add(messaggio);
    }
}
