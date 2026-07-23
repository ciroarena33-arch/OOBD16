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

        setTitle("Aggiungi partecipante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // chiudo solo la finestra
        setResizable(false);
        setSize(400, 280);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Aggiungi partecipante", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 18));
        titolo.setBounds(25, 20, 350, 25);
        contentPane.add(titolo);

        labelEmail = new JLabel("Email utente:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 14));
        labelEmail.setBounds(40, 60, 320, 20);
        contentPane.add(labelEmail);

        fieldEmail = new JTextField();
        fieldEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldEmail.setBounds(40, 85, 320, 30);
        contentPane.add(fieldEmail);

        aggiungi = new JButton("AGGIUNGI");
        aggiungi.setFont(new Font("Arial", Font.BOLD, 13));
        aggiungi.setBounds(60, 140, 120, 30);
        contentPane.add(aggiungi);

        annulla = new JButton("ANNULLA");
        annulla.setFont(new Font("Arial", Font.BOLD, 13));
        annulla.setBounds(220, 140, 120, 30);
        contentPane.add(annulla);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.PLAIN, 13));
        messaggio.setBounds(40, 190, 320, 25);
        contentPane.add(messaggio);

        aggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = fieldEmail.getText().trim();

                if (email.isEmpty()) {
                    messaggio.setText("Inserisci una email");
                } else {
                    controller.btn_aggiungiPartecipante_aggiungi(email);
                }
            }
        });

        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_aggiungiPartecipante_annulla();
            }
        });
    }
}