package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificheGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Notifiche");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(850, 520));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Notifiche", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel panelListe = new JPanel();
        panelListe.setLayout(new GridLayout(1, 2, 20, 10));

        String[] notificheInviate = {
                "A Marco: Ricordati di saldare 10€",
                "A Luca: Debito ancora in sospeso"
        };

        String[] notificheRicevute = {
                "Da Marco: Pago domani",
                "Da Luca: Ho saldato il debito"
        };

        JList<String> listaInviate = new JList<>(notificheInviate);
        JList<String> listaRicevute = new JList<>(notificheRicevute);

        JPanel panelInviate = new JPanel();
        panelInviate.setLayout(new BorderLayout(5, 5));
        JLabel labelInviate = new JLabel("Inviate", SwingConstants.CENTER);
        panelInviate.add(labelInviate, BorderLayout.NORTH);
        panelInviate.add(new JScrollPane(listaInviate), BorderLayout.CENTER);

        JPanel panelRicevute = new JPanel();
        panelRicevute.setLayout(new BorderLayout(5, 5));
        JLabel labelRicevute = new JLabel("Ricevute", SwingConstants.CENTER);
        panelRicevute.add(labelRicevute, BorderLayout.NORTH);
        panelRicevute.add(new JScrollPane(listaRicevute), BorderLayout.CENTER);

        panelListe.add(panelInviate);
        panelListe.add(panelRicevute);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton creaNotifica = new JButton("CREA NOTIFICA");
        JButton tornaHome = new JButton("TORNA HOME");

        panelBottoni.add(creaNotifica);
        panelBottoni.add(tornaHome);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(panelListe, BorderLayout.CENTER);
        panel.add(panelBottoni, BorderLayout.SOUTH);

        panelPrincipale.add(panel);

        creaNotifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreaNotificaGUI.main(null);
            }
        });

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