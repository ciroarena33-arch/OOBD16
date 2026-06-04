package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportGeneraleGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Report generale");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(750, 500));
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Report generale", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel gruppiAttivi = new JLabel("Gruppi attivi: 3");
        JLabel speseRegistrate = new JLabel("Spese registrate: 12");
        JLabel totaleSpeso = new JLabel("Totale speso: 350.00 €");
        JLabel saldoComplessivo = new JLabel("Saldo complessivo: +20.00 €");
        JLabel debitiAperti = new JLabel("Debiti aperti: 2");
        JLabel creditiAperti = new JLabel("Crediti aperti: 1");

        JButton tornaHome = new JButton("TORNA HOME");

        panel.add(titolo);
        panel.add(gruppiAttivi);
        panel.add(speseRegistrate);
        panel.add(totaleSpeso);
        panel.add(saldoComplessivo);
        panel.add(debitiAperti);
        panel.add(creditiAperti);
        panel.add(tornaHome);

        panelPrincipale.add(panel);

        tornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomeGUI.main(null);
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}