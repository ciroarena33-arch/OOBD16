package gui.utente;

import javax.swing.*;

import control.UtenteController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.movimento.ReportGeneraleGUI;
import control.NotificaController;

public class HomeGUI extends JFrame {

    private UtenteController controller;

    public HomeGUI(UtenteController controller) {
        super("Home");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 50));
        logo.setBounds(250, 45, 500, 70);

        JLabel benvenuto = new JLabel("Benvenuto, "+controller.getUtente().getNome(), SwingConstants.CENTER);
        benvenuto.setFont(new Font("Arial", Font.BOLD, 22));
        benvenuto.setBounds(250, 130, 500, 35);

        JLabel descrizione = new JLabel("Gestisci le spese di gruppo in modo semplice e trasparente", SwingConstants.CENTER);
        descrizione.setFont(new Font("Arial", Font.BOLD, 14));
        descrizione.setBounds(200, 175, 600, 30);

        JButton mieiGruppi = new JButton("I MIEI GRUPPI");
        mieiGruppi.setFont(new Font("Arial", Font.BOLD, 16));
        mieiGruppi.setBounds(315, 355, 400, 40);

        JButton reportGenerale = new JButton("REPORT GENERALE");
        reportGenerale.setFont(new Font("Arial", Font.BOLD, 16));
        reportGenerale.setBounds(315, 300, 400, 40);

        JButton datiUtente = new JButton("VISUALIZZA DATI UTENTE");
        datiUtente.setFont(new Font("Arial", Font.BOLD, 16));
        datiUtente.setBounds(315, 245, 400, 40);

        JButton visualizzaNotifiche = new JButton("CENTRO NOTIFICHE");
        visualizzaNotifiche.setFont(new Font("Arial", Font.BOLD, 16));
        visualizzaNotifiche.setBounds(315, 410, 400, 40);

        JButton esci = new JButton("ESCI");
        esci.setFont(new Font("Arial", Font.BOLD, 16));
        esci.setBounds(315, 465, 400, 40);

        JLabel messaggio = new JLabel("Seleziona un'operazione", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 530, 500, 35);

        panel.add(logo);
        panel.add(benvenuto);
        panel.add(descrizione);
        panel.add(mieiGruppi);
        panel.add(reportGenerale);
        panel.add(datiUtente);
        panel.add(visualizzaNotifiche);
        panel.add(esci);
        panel.add(messaggio);

        mieiGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_mieiGruppi();
            }
        });

        reportGenerale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_reportGenerale();
            }
        });

        datiUtente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_datiUtente();
            }
        });

        visualizzaNotifiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_visualizzaNotifiche();
            }
        });

        esci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_home_esci();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}