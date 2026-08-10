package gui.utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.UtenteController;

public class RegistrazioneGUI extends JFrame {

    private UtenteController controller;

    public RegistrazioneGUI(UtenteController controller) {
        super();
        setTitle("Registrazione");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logo.setForeground(new Color(30, 41, 59));
        logo.setBounds(100, 30, 560, 40);

        JLabel titolo = new JLabel("Registrazione utente", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titolo.setForeground(new Color(70, 85, 105));
        titolo.setBounds(100, 80, 560, 30);

        JLabel nome = new JLabel("Nome");
        nome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nome.setForeground(new Color(70, 85, 105));
        JTextField inserisciNome = new JTextField();
        inserisciNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel cognome = new JLabel("Cognome");
        cognome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        cognome.setForeground(new Color(70, 85, 105));
        JTextField inserisciCognome = new JTextField();
        inserisciCognome.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel email = new JLabel("Email istituzionale");
        email.setFont(new Font("Segoe UI", Font.BOLD, 13));
        email.setForeground(new Color(70, 85, 105));
        JTextField inserisciEmail = new JTextField();
        inserisciEmail.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Segoe UI", Font.BOLD, 13));
        password.setForeground(new Color(70, 85, 105));
        JPasswordField inserisciPassword = new JPasswordField();
        inserisciPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        int startY = 130;
        int stepY = 68;
        nome.setBounds(100, startY + 8, 150, 26);
        inserisciNome.setBounds(270, startY, 390, 40);
        cognome.setBounds(100, startY + stepY + 8, 150, 26);
        inserisciCognome.setBounds(270, startY + stepY, 390, 40);
        email.setBounds(100, startY + stepY * 2 + 8, 150, 26);
        inserisciEmail.setBounds(270, startY + stepY * 2, 390, 40);
        password.setBounds(100, startY + stepY * 3 + 8, 150, 26);
        inserisciPassword.setBounds(270, startY + stepY * 3, 390, 40);

        JCheckBox mostraPassword = new JCheckBox("Mostra password");
        mostraPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        mostraPassword.setOpaque(false);
        mostraPassword.setBounds(520, startY + stepY * 3 + 8, 140, 28);

        JButton registrati = new JButton("REGISTRATI");
        registrati.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registrati.setBackground(new Color(60, 120, 216));
        registrati.setForeground(Color.WHITE);
        registrati.setFocusPainted(false);

        JButton tornaLogin = new JButton("TORNA AL LOGIN");
        tornaLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaLogin.setBackground(new Color(110, 120, 135));
        tornaLogin.setForeground(Color.WHITE);
        tornaLogin.setFocusPainted(false);

        registrati.setBounds(100, 410, 270, 44);
        tornaLogin.setBounds(390, 410, 270, 44);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(100, 468, 560, 26);

        panel.add(logo);
        panel.add(titolo);
        panel.add(nome);
        panel.add(inserisciNome);
        panel.add(cognome);
        panel.add(inserisciCognome);
        panel.add(email);
        panel.add(inserisciEmail);
        panel.add(password);
        panel.add(inserisciPassword);
        panel.add(mostraPassword);
        panel.add(registrati);
        panel.add(tornaLogin);
        panel.add(messaggio);

        mostraPassword.addActionListener(e -> {
            if (mostraPassword.isSelected()) {
                inserisciPassword.setEchoChar((char) 0);
            } else {
                inserisciPassword.setEchoChar('•');
            }
        });

        registrati.addActionListener(e -> {
            String nome1 = inserisciNome.getText();
            String cognome1 = inserisciCognome.getText();
            String email1 = inserisciEmail.getText().trim();
            String password1 = new String(inserisciPassword.getPassword()).trim();
            if (nome1.isEmpty() || cognome1.isEmpty() || email1.isEmpty() || password1.isEmpty()) {
                messaggio.setText("Compila tutti i campi");
            } else {
                messaggio.setText("Registrazione effettuata");
                controller.btn_registrazione_registrati(email1, password1, nome1, cognome1);
            }
        });

        tornaLogin.addActionListener(e -> controller.btn_registrazione_tornaLogin());

        setContentPane(panel);
        setSize(760, 530);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
