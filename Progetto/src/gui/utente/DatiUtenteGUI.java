package gui.utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import control.UtenteController;

public class DatiUtenteGUI extends JFrame {

    private UtenteController controller;

    public DatiUtenteGUI(UtenteController controller, String nome, String cognome, String password, String email, String telefono) {
        super();
        setTitle("Dati Utente");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));
        setContentPane(panel);

        JLabel titolo = new JLabel("Dati Utente", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(30, 20, 450, 35);
        panel.add(titolo);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelNome.setForeground(new Color(70, 85, 105));
        labelNome.setBounds(35, 75, 100, 36);
        panel.add(labelNome);

        JTextField fieldNome = new JTextField(nome);
        fieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNome.setBounds(140, 75, 340, 36);
        panel.add(fieldNome);

        JLabel labelCognome = new JLabel("Cognome:");
        labelCognome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelCognome.setForeground(new Color(70, 85, 105));
        labelCognome.setBounds(35, 123, 100, 36);
        panel.add(labelCognome);

        JTextField fieldCognome = new JTextField(cognome);
        fieldCognome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldCognome.setBounds(140, 123, 340, 36);
        panel.add(fieldCognome);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelPassword.setForeground(new Color(70, 85, 105));
        labelPassword.setBounds(35, 171, 100, 36);
        panel.add(labelPassword);

        JPasswordField fieldPassword = new JPasswordField(password);
        fieldPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldPassword.setBounds(140, 171, 240, 36);
        panel.add(fieldPassword);

        JCheckBox mostraPassword = new JCheckBox("Mostra");
        mostraPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        mostraPassword.setOpaque(false);
        mostraPassword.setBounds(390, 171, 90, 36);
        panel.add(mostraPassword);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelEmail.setForeground(new Color(70, 85, 105));
        labelEmail.setBounds(35, 219, 100, 36);
        panel.add(labelEmail);

        JTextField fieldEmail = new JTextField(email);
        fieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldEmail.setBounds(140, 219, 340, 36);
        fieldEmail.setEditable(false);
        fieldEmail.setBackground(new Color(230, 232, 240));
        panel.add(fieldEmail);

        JLabel labelTelefono = new JLabel("Telefono:");
        labelTelefono.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelTelefono.setForeground(new Color(70, 85, 105));
        labelTelefono.setBounds(35, 267, 100, 36);
        panel.add(labelTelefono);

        JTextField fieldTelefono = new JTextField(telefono == null || telefono.isEmpty() ? "" : telefono);
        fieldTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldTelefono.setBounds(140, 267, 340, 36);
        panel.add(fieldTelefono);

        JButton salvaModifiche = new JButton("SALVA MODIFICHE");
        salvaModifiche.setFont(new Font("Segoe UI", Font.BOLD, 13));
        salvaModifiche.setBackground(new Color(60, 120, 216));
        salvaModifiche.setForeground(Color.WHITE);
        salvaModifiche.setFocusPainted(false);
        salvaModifiche.setBounds(35, 325, 215, 40);
        panel.add(salvaModifiche);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaHome.setBackground(new Color(110, 120, 135));
        tornaHome.setForeground(Color.WHITE);
        tornaHome.setFocusPainted(false);
        tornaHome.setBounds(265, 325, 215, 40);
        panel.add(tornaHome);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(35, 378, 445, 25);
        panel.add(messaggio);

        mostraPassword.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                fieldPassword.setEchoChar((char) 0);
            } else {
                fieldPassword.setEchoChar('•');
            }
        });

        salvaModifiche.addActionListener(e -> {
            String nomeInput = fieldNome.getText().trim();
            String cognomeInput = fieldCognome.getText().trim();
            String telInput = fieldTelefono.getText().trim();
            String pwInput = new String(fieldPassword.getPassword()).trim();
            if (nomeInput.isEmpty() || cognomeInput.isEmpty() || pwInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome, cognome e password non possono essere vuoti");
            } else {
                controller.btn_datiUtente_salvaModifiche(pwInput, nomeInput, cognomeInput, telInput);
            }
        });

        tornaHome.addActionListener(e -> controller.btn_datiUtente_tornaHome());

        setSize(520, 440);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}