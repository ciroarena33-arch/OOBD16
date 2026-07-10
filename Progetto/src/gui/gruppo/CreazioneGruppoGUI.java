package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.GruppoController;

public class CreazioneGruppoGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private GruppoController controller;

    private JPanel contentPane;
    private JLabel titolo;
    private JLabel nomeGruppo;
    private JTextField scriviNome;
    private JLabel categoriaGruppo;
    private JComboBox<String> scegliCategoria;
    private JButton confermaCreazione;
    private JButton tornaHomeGruppi;
    private JLabel messaggio;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreazioneGruppoGUI frame = new CreazioneGruppoGUI(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CreazioneGruppoGUI(GruppoController controller) {
        this.controller = controller;

        setTitle("Creazione gruppo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(480, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        titolo = new JLabel("Crea nuovo gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 26));
        titolo.setBounds(50, 30, 380, 35);
        contentPane.add(titolo);

        nomeGruppo = new JLabel("Nome gruppo:");
        nomeGruppo.setFont(new Font("Arial", Font.BOLD, 14));
        nomeGruppo.setBounds(60, 95, 360, 20);
        contentPane.add(nomeGruppo);

        scriviNome = new JTextField();
        scriviNome.setFont(new Font("Arial", Font.PLAIN, 15));
        scriviNome.setBounds(60, 120, 360, 30);
        contentPane.add(scriviNome);

        categoriaGruppo = new JLabel("Tipologia gruppo:");
        categoriaGruppo.setFont(new Font("Arial", Font.BOLD, 14));
        categoriaGruppo.setBounds(60, 175, 360, 20);
        contentPane.add(categoriaGruppo);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        scegliCategoria = new JComboBox<>(tipiGruppo);
        scegliCategoria.setFont(new Font("Arial", Font.PLAIN, 14));
        scegliCategoria.setBounds(60, 200, 360, 30);
        contentPane.add(scegliCategoria);

        confermaCreazione = new JButton("CONFERMA CREAZIONE");
        confermaCreazione.setFont(new Font("Arial", Font.BOLD, 13));
        confermaCreazione.setBounds(60, 275, 160, 35);
        contentPane.add(confermaCreazione);

        tornaHomeGruppi = new JButton("ANNULLA");
        tornaHomeGruppi.setFont(new Font("Arial", Font.BOLD, 13));
        tornaHomeGruppi.setBounds(260, 275, 160, 35);
        contentPane.add(tornaHomeGruppi);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 13));
        messaggio.setBounds(60, 335, 360, 25);
        contentPane.add(messaggio);

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
                controller.btn_creazioneGruppo_tornaHome();
            }
        });
    }
}