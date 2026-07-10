package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneGUI extends JFrame {

    private UtenteController controller;

    public RegistrazioneGUI(UtenteController controller) {
        super("Registrazione");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setBounds(250, 45, 500, 70);

        JLabel nome = new JLabel("Nome");
        nome.setFont(new Font("Arial", Font.BOLD, 16));
        nome.setBounds(315, 145, 180, 30);

        JTextField inserisciNome = new JTextField();
        inserisciNome.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciNome.setBounds(455, 145, 260, 35);

        JLabel cognome = new JLabel("Cognome");
        cognome.setFont(new Font("Arial", Font.BOLD, 16));
        cognome.setBounds(315, 200, 180, 30);

        JTextField inserisciCognome = new JTextField();
        inserisciCognome.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciCognome.setBounds(455, 200, 260, 35);

        JLabel email = new JLabel("Email istituzionale");
        email.setFont(new Font("Arial", Font.BOLD, 16));
        email.setBounds(315, 255, 180, 30);

        JTextField inserisciEmail = new JTextField();
        inserisciEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciEmail.setBounds(455, 255, 260, 35);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setBounds(315, 310, 180, 30);

        JPasswordField inserisciPassword = new JPasswordField();
        inserisciPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciPassword.setBounds(455, 310, 260, 35);

        JCheckBox mostraPassword = new JCheckBox("Mostra password");
        mostraPassword.setFont(new Font("Arial", Font.BOLD, 13));
        mostraPassword.setBounds(730, 317, 180, 25);

        JButton registrati = new JButton("REGISTRATI");
        registrati.setFont(new Font("Arial", Font.BOLD, 16));
        registrati.setBounds(315, 390, 400, 40);

        JButton tornaLogin = new JButton("TORNA AL LOGIN");
        tornaLogin.setFont(new Font("Arial", Font.BOLD, 16));
        tornaLogin.setBounds(315, 445, 400, 40);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 505, 500, 35);

        panel.add(logo);
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

        mostraPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostraPassword.isSelected()) {
                    inserisciPassword.setEchoChar((char) 0);
                } else {
                    inserisciPassword.setEchoChar('•');
                }
            }
        });

        registrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome1 = inserisciNome.getText();
                String cognome1 = inserisciCognome.getText();
                String email1 = inserisciEmail.getText();
                String password1 = new String(inserisciPassword.getPassword());

                if (nome1.isEmpty() || cognome1.isEmpty() || email1.isEmpty() || password1.isEmpty()) {
                    messaggio.setText("Compila tutti i campi");
                } else {
                    messaggio.setText("Registrazione effettuata");
                    controller.btn_registrazione_registrati(email1, password1, nome1, cognome1);
                }
            }
        });

        tornaLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_registrazione_tornaLogin();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}