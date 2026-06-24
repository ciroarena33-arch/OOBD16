package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.EventQueue;

public class LoginGUI extends JFrame {

    private UtenteController controller;

    public LoginGUI(UtenteController controller) {
        super("Login");
        this.controller=controller;
        
        JPanel panelPrincipale = new JPanel();
        JPanel panel = new JPanel();

        panelPrincipale.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(700, 560));
        panel.setLayout(new GridLayout(9, 1, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel email = new JLabel("Email istituzionale");
        email.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField inserisciEmail = new JTextField();
        inserisciEmail.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 16));

        JPasswordField inserisciPassword = new JPasswordField();
        inserisciPassword.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton accedi = new JButton("ACCEDI");
        accedi.setFont(new Font("Arial", Font.BOLD, 16));

        JButton registrati = new JButton("NON HAI UN ACCOUNT? REGISTRATI");
        registrati.setFont(new Font("Arial", Font.BOLD, 14));

        JButton esci = new JButton("ESCI");
        esci.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(logo);
        panel.add(email);
        panel.add(inserisciEmail);
        panel.add(password);
        panel.add(inserisciPassword);
        panel.add(accedi);
        panel.add(registrati);
        panel.add(esci);
        panel.add(messaggio);

        panelPrincipale.add(panel);

        accedi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email1 = inserisciEmail.getText();
                String password1 = inserisciPassword.getText();

                if (email1.isEmpty() || password1.isEmpty()) {
                    messaggio.setText("Credenziali non valide");
                } else {
                    messaggio.setText("Accesso effettuato");
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

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}