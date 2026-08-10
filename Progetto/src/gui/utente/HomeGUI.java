package gui.utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.UtenteController;

public class HomeGUI extends JFrame {

    private UtenteController controller;

    public HomeGUI(UtenteController controller) {
        super();
        setTitle("Home");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        logo.setForeground(new Color(30, 41, 59));
        logo.setBounds(100, 40, 580, 45);

        JLabel benvenuto = new JLabel("Benvenuto, " + controller.getUtente().getNome(), SwingConstants.CENTER);
        benvenuto.setFont(new Font("Segoe UI", Font.BOLD, 20));
        benvenuto.setForeground(new Color(70, 85, 105));
        benvenuto.setBounds(100, 100, 580, 32);

        JLabel descrizione = new JLabel("Gestisci le spese di gruppo in modo semplice e trasparente", SwingConstants.CENTER);
        descrizione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descrizione.setForeground(new Color(100, 116, 139));
        descrizione.setBounds(60, 140, 660, 28);

        JButton mieiGruppi = new JButton("I MIEI GRUPPI");
        mieiGruppi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        mieiGruppi.setBackground(new Color(60, 120, 216));
        mieiGruppi.setForeground(Color.WHITE);
        mieiGruppi.setFocusPainted(false);

        JButton reportGenerale = new JButton("REPORT GENERALE");
        reportGenerale.setFont(new Font("Segoe UI", Font.BOLD, 14));
        reportGenerale.setBackground(new Color(60, 120, 216));
        reportGenerale.setForeground(Color.WHITE);
        reportGenerale.setFocusPainted(false);

        JButton datiUtente = new JButton("VISUALIZZA DATI UTENTE");
        datiUtente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        datiUtente.setBackground(new Color(60, 120, 216));
        datiUtente.setForeground(Color.WHITE);
        datiUtente.setFocusPainted(false);

        JButton visualizzaNotifiche = new JButton("CENTRO NOTIFICHE");
        visualizzaNotifiche.setFont(new Font("Segoe UI", Font.BOLD, 14));
        visualizzaNotifiche.setBackground(new Color(60, 120, 216));
        visualizzaNotifiche.setForeground(Color.WHITE);
        visualizzaNotifiche.setFocusPainted(false);

        mieiGruppi.setBounds(60, 210, 290, 60);
        reportGenerale.setBounds(380, 210, 290, 60);
        datiUtente.setBounds(60, 300, 290, 60);
        visualizzaNotifiche.setBounds(380, 300, 290, 60);

        JButton esci = new JButton("ESCI");
        esci.setFont(new Font("Segoe UI", Font.BOLD, 13));
        esci.setBackground(new Color(210, 60, 60));
        esci.setForeground(Color.WHITE);
        esci.setFocusPainted(false);
        esci.setBounds(570, 20, 100, 38);

        JLabel messaggio = new JLabel("Seleziona un'operazione", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setForeground(new Color(100, 116, 139));
        messaggio.setBounds(100, 385, 580, 30);

        panel.add(logo);
        panel.add(benvenuto);
        panel.add(descrizione);
        panel.add(mieiGruppi);
        panel.add(reportGenerale);
        panel.add(datiUtente);
        panel.add(visualizzaNotifiche);
        panel.add(esci);
        panel.add(messaggio);

        mieiGruppi.addActionListener(e -> controller.btn_home_mieiGruppi());
        reportGenerale.addActionListener(e -> controller.btn_home_reportGenerale());
        datiUtente.addActionListener(e -> controller.btn_home_datiUtente());
        visualizzaNotifiche.addActionListener(e -> controller.btn_home_visualizzaNotifiche());
        esci.addActionListener(e -> controller.btn_home_esci());

        setContentPane(panel);
        setSize(730, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
