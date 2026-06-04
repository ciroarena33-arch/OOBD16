package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreaNotificaGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Crea notifica");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(650, 420));
        panel.setLayout(new BorderLayout(10, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Crea notifica", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel panelCampi = new JPanel();
        panelCampi.setLayout(new GridLayout(2, 2, 10, 15));

        JLabel labelDebito = new JLabel("Debito:");
        String[] debiti = {
                "Marco deve 10.00 €",
                "Luca deve 10.00 €",
                "Davide deve 5.00 €"
        };
        JComboBox<String> scegliDebito = new JComboBox<>(debiti);

        JLabel labelDescrizione = new JLabel("Descrizione:");
        JTextField fieldDescrizione = new JTextField();

        panelCampi.add(labelDebito);
        panelCampi.add(scegliDebito);

        panelCampi.add(labelDescrizione);
        panelCampi.add(fieldDescrizione);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton inviaNotifica = new JButton("INVIA NOTIFICA");
        JButton annulla = new JButton("ANNULLA");

        panelBottoni.add(inviaNotifica);
        panelBottoni.add(annulla);

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

        inviaNotifica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String debito = scegliDebito.getSelectedItem().toString();
                String descrizione = fieldDescrizione.getText();

                if (descrizione.isEmpty()) {
                    messaggio.setText("Inserisci una descrizione");
                } else {
                    messaggio.setText("Notifica inviata per: " + debito);
                }
            }
        });

        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}