package gui.utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import control.UtenteController;
import model.Utente;

public class DatiUtenteGUI extends JFrame {
	
	private UtenteController controller;
	
    public DatiUtenteGUI(UtenteController controller) {
        super("Dati utente");
        this.controller=controller;

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 520));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Dati utente", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel panelCampi = new JPanel();
        panelCampi.setLayout(new GridLayout(5, 2, 10, 15));

        JLabel labelNome = new JLabel("Nome:");
        JTextField fieldNome = new JTextField("Davide");

        JLabel labelCognome = new JLabel("Cognome:");
        JTextField fieldCognome = new JTextField("Cotena");

        JLabel labelEmail = new JLabel("Email:");
        JTextField fieldEmail = new JTextField("davide@unina.it");
        fieldEmail.setEditable(false);

        JLabel labelTelefono = new JLabel("Telefono:");
        JTextField fieldTelefono = new JTextField("");

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField fieldPassword = new JPasswordField("1234");
        
        panelCampi.add(labelNome);
        panelCampi.add(fieldNome);

        panelCampi.add(labelCognome);
        panelCampi.add(fieldCognome);

        panelCampi.add(labelPassword);
        panelCampi.add(fieldPassword);
        
        panelCampi.add(labelEmail);
        panelCampi.add(fieldEmail);

        panelCampi.add(labelTelefono);
        panelCampi.add(fieldTelefono);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton SalvaModifiche = new JButton("SALVA MODIFICHE");
        JButton TornaHome = new JButton("TORNA HOME");

        panelBottoni.add(SalvaModifiche);
        panelBottoni.add(TornaHome);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        JPanel panelBasso = new JPanel();
        panelBasso.setLayout(new GridLayout(2, 1, 10, 10));
        panelBasso.add(panelBottoni);
        panelBasso.add(messaggio);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(panelCampi, BorderLayout.CENTER);
        panel.add(panelBasso, BorderLayout.SOUTH);

        panelPrincipale.add(panel);
        JCheckBox showPasswordCheckBox= new JCheckBox("Mostra Password");
        panel.add(showPasswordCheckBox, BorderLayout.EAST);
        showPasswordCheckBox.addItemListener(e->{
        	if(e.getStateChange()==ItemEvent.SELECTED) {
        		fieldPassword.setEchoChar('\u0000');
        	}
        	else {
        		fieldPassword.setEchoChar('*');
        	}
        });

        SalvaModifiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = fieldNome.getText();
                String cognome = fieldCognome.getText();
                String telefono = fieldTelefono.getText();
                String password = fieldPassword.getText();

                if (nome.isEmpty() || cognome.isEmpty() || password.isEmpty()) {
                    messaggio.setText("Nome, cognome e password non possono essere vuoti");
                } else {
                    controller.btn_datiUtente_salvaModifiche(password, nome, cognome, telefono);
                }
            }
        });

        TornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_datiUtente_tornaHome();
            }
        });

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}