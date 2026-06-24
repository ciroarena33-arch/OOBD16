package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.EventQueue;

public class CreazioneGruppoGUI extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreazioneGruppoGUI frame = new CreazioneGruppoGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreazioneGruppoGUI() {
        super("Creazione gruppo");

        JPanel panelPrincipale = new JPanel();
        panelPrincipale.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 500));
        panel.setLayout(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titolo = new JLabel("Crea nuovo gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel nomeGruppo = new JLabel("Nome gruppo:");
        JTextField scriviNome = new JTextField();

        JLabel categoriaGruppo = new JLabel("Tipologia gruppo:");
        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        JComboBox<String> scegliCategoria = new JComboBox<>(tipiGruppo);

        JPanel panelBottoni = new JPanel();
        panelBottoni.setLayout(new FlowLayout());

        JButton confermaCreazione = new JButton("CONFERMA CREAZIONE");
        JButton tornaHomeGruppi = new JButton("ANNULLA");

        panelBottoni.add(confermaCreazione);
        panelBottoni.add(tornaHomeGruppi);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(titolo);
        panel.add(nomeGruppo);
        panel.add(scriviNome);
        panel.add(categoriaGruppo);
        panel.add(scegliCategoria);
        panel.add(panelBottoni);
        panel.add(messaggio);

        panelPrincipale.add(panel);

        confermaCreazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeG = scriviNome.getText();
                String categoriaG = scegliCategoria.getSelectedItem().toString();

                if (nomeG.isEmpty()) {
                    messaggio.setText("Inserire il nome del gruppo");
                } else {
                    messaggio.setText("Gruppo creato: " + nomeG + " - " + categoriaG);
                }
            }
        });

        tornaHomeGruppi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                IMieiGruppiGUI.main(null);
            }
        });

        setContentPane(panelPrincipale);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}