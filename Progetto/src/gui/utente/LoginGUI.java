package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private UtenteController controller;

    public LoginGUI(UtenteController controller) {
        super("Login");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setBounds(250, 70, 500, 70);

        JLabel email = new JLabel("Email istituzionale");
        email.setFont(new Font("Arial", Font.BOLD, 16));
        email.setBounds(315, 180, 180, 30);

        JTextField inserisciEmail = new JTextField();
        inserisciEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciEmail.setBounds(455, 180, 260, 35);

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setBounds(315, 235, 180, 30);

        JPasswordField inserisciPassword = new JPasswordField();
        inserisciPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        inserisciPassword.setBounds(455, 235, 260, 35);

        JCheckBox mostraPassword = new JCheckBox("Mostra password");
        mostraPassword.setFont(new Font("Arial", Font.BOLD, 13));
        mostraPassword.setBounds(730, 240, 180, 25);
        
        JButton accedi = new JButton("ACCEDI");
        accedi.setFont(new Font("Arial", Font.BOLD, 16));
        accedi.setBounds(315, 345, 400, 40);

        JButton registrati = new JButton("NON HAI UN ACCOUNT? REGISTRATI");
        registrati.setFont(new Font("Arial", Font.BOLD, 14));
        registrati.setBounds(315, 400, 400, 40);

        JButton esci = new JButton("ESCI");
        esci.setFont(new Font("Arial", Font.BOLD, 16));
        esci.setBounds(315, 455, 400, 40);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 515, 500, 35);

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

        accedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email1 = inserisciEmail.getText();
                String password1 = new String(inserisciPassword.getPassword());

                if (email1.isEmpty() || password1.isEmpty()) {
                    messaggio.setText("Credenziali non valide");
                } else {
                    controller.btn_login_accedi(email1, password1);
                }
            }
        });

        registrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_login_registrati();
            }
        });

        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_login_esci();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}