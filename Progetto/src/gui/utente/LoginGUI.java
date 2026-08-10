package gui.utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.UtenteController;

public class LoginGUI extends JFrame {

    private UtenteController controller;

    public LoginGUI(UtenteController controller) {
        super();
        setTitle("Login");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(new Color(30, 41, 59));
        logo.setBounds(80, 42, 420, 44);

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

        email.setBounds(80, 125, 420, 24);
        inserisciEmail.setBounds(80, 151, 420, 42);
        password.setBounds(80, 220, 420, 24);
        inserisciPassword.setBounds(80, 246, 420, 42);

        JCheckBox mostraPassword = new JCheckBox("Mostra password");
        mostraPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        mostraPassword.setOpaque(false);
        mostraPassword.setBounds(80, 300, 220, 28);

        JButton accedi = new JButton("ACCEDI");
        accedi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        accedi.setBackground(new Color(60, 120, 216));
        accedi.setForeground(Color.WHITE);
        accedi.setFocusPainted(false);
        accedi.setBounds(80, 345, 420, 46);

        JButton registrati = new JButton("Non hai un account? Registrati");
        registrati.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registrati.setBackground(new Color(110, 120, 135));
        registrati.setForeground(Color.WHITE);
        registrati.setFocusPainted(false);
        registrati.setBounds(80, 407, 420, 42);

        JButton esci = new JButton("ESCI");
        esci.setFont(new Font("Segoe UI", Font.BOLD, 13));
        esci.setBackground(new Color(210, 60, 60));
        esci.setForeground(Color.WHITE);
        esci.setFocusPainted(false);
        esci.setBounds(404, 38, 96, 38);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(80, 466, 420, 24);

        panel.add(logo);
        panel.add(email);
        panel.add(inserisciEmail);
        panel.add(password);
        panel.add(inserisciPassword);
        panel.add(mostraPassword);
        panel.add(accedi);
        panel.add(registrati);
        panel.add(esci);
        panel.add(messaggio);

        mostraPassword.addActionListener(e -> {
            if (mostraPassword.isSelected()) {
                inserisciPassword.setEchoChar((char) 0);
            } else {
                inserisciPassword.setEchoChar('•');
            }
        });

        accedi.addActionListener(e -> {
            String email1 = inserisciEmail.getText();
            String password1 = new String(inserisciPassword.getPassword());
            if (email1.isEmpty() || password1.isEmpty()) {
                messaggio.setText("Credenziali non valide");
            } else {
                controller.btn_login_accedi(email1, password1);
            }
        });

        registrati.addActionListener(e -> controller.btn_login_registrati());
        esci.addActionListener(e -> controller.btn_login_esci());

        setContentPane(panel);
        setSize(580, 530);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
