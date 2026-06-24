package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import java.awt.EventQueue;
import gui.gruppo.IMieiGruppiGUI;
import gui.movimento.ReportGeneraleGUI;
import gui.notifica.NotificheGUI;

public class HomeGUI extends JFrame {

    private UtenteController controller;

    public HomeGUI(UtenteController controller) {
        super("Home");

        JPanel panelPrincipale = new JPanel();
        JPanel panel = new JPanel();

        panelPrincipale.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(600, 500));
        panel.setLayout(new GridLayout(9, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel logo = new JLabel("UninaMoneySplit");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));

        JLabel benvenuto = new JLabel("Benvenuto, Utente");
        benvenuto.setHorizontalAlignment(SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel descrizione = new JLabel("Gestisci le spese di gruppo in modo semplice e trasparente");
        descrizione.setHorizontalAlignment(SwingConstants.CENTER);
        descrizione.setFont(new Font("Arial", Font.BOLD, 14));

        JButton mieiGruppi = new JButton("I MIEI GRUPPI");
        JButton reportGenerale = new JButton("REPORT GENERALE");
        JButton datiUtente = new JButton("VISUALIZZA DATI UTENTE");
        JButton visualizzaNotifiche = new JButton("CENTRO NOTIFICHE");
        JButton esci = new JButton("ESCI");

        JLabel messaggio = new JLabel("Seleziona un'operazione");
        messaggio.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(logo);
        panel.add(benvenuto);
        panel.add(descrizione);
        panel.add(mieiGruppi);
        panel.add(reportGenerale);
        panel.add(datiUtente);
        panel.add(visualizzaNotifiche);
        panel.add(esci);
        panel.add(messaggio);

        panelPrincipale.add(panel);

        mieiGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_mieiGruppi();
            }
        });

        reportGenerale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReportGeneraleGUI.main(null);
            }
        });

        datiUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_datiUtente();}
        });
        
        visualizzaNotifiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NotificheGUI.main(null);
            }
        });

        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_esci();
            }
        });

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}