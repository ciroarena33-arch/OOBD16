package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class I_Miei_GruppiGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("I miei gruppi");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 500));
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titolo = new JLabel("I miei gruppi", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        String[] gruppi = {
                "Viaggio Roma",
                "Coinquilini Napoli",
                "Studio Basi di Dati"
        };

        JList<String> listaGruppi = new JList<>(gruppi);
        listaGruppi.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scrollPane = new JScrollPane(listaGruppi);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton apriGruppo = new JButton("APRI GRUPPO");
        JButton creaGruppo = new JButton("CREA NUOVO GRUPPO");
        JButton tornaHome = new JButton("TORNA HOME");

        panelBottoni.add(apriGruppo);
        panelBottoni.add(creaGruppo);
        panelBottoni.add(tornaHome);

        panel.add(titolo, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBottoni, BorderLayout.SOUTH);

        panelPrincipale.add(panel);

        apriGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gruppoSelezionato = listaGruppi.getSelectedValue();

                if (gruppoSelezionato == null) {
                    JOptionPane.showMessageDialog(frame, "Seleziona prima un gruppo");
                } else {
                    DettaglioGruppoGUI.nomeGruppoSelezionato = gruppoSelezionato;
                    frame.dispose();
                    DettaglioGruppoGUI.main(null);
                }
            }
        });

        creaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CreazioneGruppoGUI.main(null);
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