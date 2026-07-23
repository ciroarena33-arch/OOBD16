package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class DatiUtenteGUI extends JFrame {

    private UtenteController controller;

    public DatiUtenteGUI(UtenteController controller) {
        super("Dati utente");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titolo = new JLabel("Dati utente", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setBounds(250, 45, 500, 70);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 16));
        labelNome.setBounds(315, 145, 180, 30);

        JTextField fieldNome = new JTextField(controller.getUtente().getNome());
        fieldNome.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldNome.setBounds(455, 145, 260, 35);

        JLabel labelCognome = new JLabel("Cognome:");
        labelCognome.setFont(new Font("Arial", Font.BOLD, 16));
        labelCognome.setBounds(315, 200, 180, 30);

        JTextField fieldCognome = new JTextField(controller.getUtente().getCognome());
        fieldCognome.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldCognome.setBounds(455, 200, 260, 35);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Arial", Font.BOLD, 16));
        labelPassword.setBounds(315, 255, 180, 30);

        JPasswordField fieldPassword = new JPasswordField(controller.getUtente().getPassword());
        fieldPassword.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldPassword.setBounds(455, 255, 260, 35);

        JCheckBox mostraPassword = new JCheckBox("Mostra password");
        mostraPassword.setFont(new Font("Arial", Font.BOLD, 13));
        mostraPassword.setBounds(730, 262, 180, 25);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 16));
        labelEmail.setBounds(315, 310, 180, 30);

        JTextField fieldEmail = new JTextField(controller.getUtente().getEmailIstituzionale());
        fieldEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldEmail.setBounds(455, 310, 260, 35);
        fieldEmail.setEditable(false);

        JLabel labelTelefono = new JLabel("Telefono:");
        labelTelefono.setFont(new Font("Arial", Font.BOLD, 16));
        labelTelefono.setBounds(315, 365, 180, 30);

        String telefono=controller.getUtente().getTelefono();
        JTextField fieldTelefono = new JTextField(telefono.isEmpty()?"":telefono);
        fieldTelefono.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldTelefono.setBounds(455, 365, 260, 35);

        JButton salvaModifiche = new JButton("SALVA MODIFICHE");
        salvaModifiche.setFont(new Font("Arial", Font.BOLD, 16));
        salvaModifiche.setBounds(315, 445, 190, 40);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 16));
        tornaHome.setBounds(525, 445, 190, 40);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 510, 500, 35);

        panel.add(titolo);

        panel.add(labelNome);
        panel.add(fieldNome);

        panel.add(labelCognome);
        panel.add(fieldCognome);

        panel.add(labelPassword);
        panel.add(fieldPassword);
        panel.add(mostraPassword);

        panel.add(labelEmail);
        panel.add(fieldEmail);

        panel.add(labelTelefono);
        panel.add(fieldTelefono);

        panel.add(salvaModifiche);
        panel.add(tornaHome);
        panel.add(messaggio);

        mostraPassword.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                fieldPassword.setEchoChar((char) 0);
            } else {
                fieldPassword.setEchoChar('•');
            }
        });

        salvaModifiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                String cognome = fieldCognome.getText();
                String telefono = fieldTelefono.getText();
                String password = new String(fieldPassword.getPassword());

                if (nome.isEmpty() || cognome.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null,"Nome, cognome e password non possono essere vuoti");
                } else {
                    controller.btn_datiUtente_salvaModifiche(password, nome, cognome, telefono);
                }
            }
        });

        tornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_datiUtente_tornaHome();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}