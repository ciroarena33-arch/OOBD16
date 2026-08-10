package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import control.GruppoController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class CreazioneGruppoGUI extends JFrame {

    private GruppoController controller;
    private JTextField scriviNome;
    private JComboBox<String> scegliCategoria;
    private JTextField fieldDestinazione;
    private JCalendarComboBox fieldDataPartenza;
    private JCalendarComboBox fieldDataRitorno;
    private JTextField fieldProvincia;
    private JTextField fieldCitta;
    private JTextField fieldVia;
    private JTextField fieldNumeroCivico;
    private JTextField fieldEsame;
    private JCalendarComboBox fieldDataAppello;

    public CreazioneGruppoGUI(GruppoController controller) {
        super();
        setTitle("Creazione Gruppo");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        JLabel titolo = new JLabel("Crea nuovo gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(50, 20, 500, 36);

        JLabel nomeGruppo = new JLabel("Nome gruppo:");
        nomeGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nomeGruppo.setForeground(new Color(70, 85, 105));
        nomeGruppo.setBounds(60, 80, 150, 30);

        scriviNome = new JTextField();
        scriviNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        scriviNome.setBounds(220, 80, 300, 30);

        JLabel categoriaGruppo = new JLabel("Tipologia:");
        categoriaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        categoriaGruppo.setForeground(new Color(70, 85, 105));
        categoriaGruppo.setBounds(60, 125, 150, 30);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        scegliCategoria = new JComboBox<>(tipiGruppo);
        scegliCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        scegliCategoria.setBounds(220, 125, 300, 30);

        JPanel panelSpecifico = new JPanel();
        panelSpecifico.setLayout(null);
        panelSpecifico.setBackground(new Color(245, 245, 250));
        panelSpecifico.setBounds(60, 175, 460, 200);

        JButton confermaCreazione = new JButton("CONFERMA CREAZIONE");
        confermaCreazione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        confermaCreazione.setBackground(new Color(60, 120, 216));
        confermaCreazione.setForeground(Color.WHITE);
        confermaCreazione.setFocusPainted(false);
        confermaCreazione.setBounds(60, 390, 460, 38);

        JButton annulla = new JButton("ANNULLA");
        annulla.setFont(new Font("Segoe UI", Font.BOLD, 13));
        annulla.setBackground(new Color(110, 120, 135));
        annulla.setForeground(Color.WHITE);
        annulla.setFocusPainted(false);
        annulla.setBounds(60, 445, 220, 38);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaHome.setBackground(new Color(110, 120, 135));
        tornaHome.setForeground(Color.WHITE);
        tornaHome.setFocusPainted(false);
        tornaHome.setBounds(300, 445, 220, 38);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.BOLD, 13));
        messaggio.setBounds(60, 500, 460, 25);

        panel.add(titolo);
        panel.add(nomeGruppo);
        panel.add(scriviNome);
        panel.add(categoriaGruppo);
        panel.add(scegliCategoria);
        panel.add(panelSpecifico);
        panel.add(confermaCreazione);
        panel.add(annulla);
        panel.add(tornaHome);
        panel.add(messaggio);

        scegliCategoria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSpecifico.removeAll();
                String categoria = scegliCategoria.getSelectedItem().toString();

                if (categoria.equals("VIAGGIO")) {
                    JLabel destinazione = new JLabel("Destinazione:");
                    destinazione.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    destinazione.setForeground(new Color(70, 85, 105));
                    destinazione.setBounds(0, 10, 140, 30);

                    fieldDestinazione = new JTextField();
                    fieldDestinazione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDestinazione.setBounds(145, 10, 260, 30);

                    JLabel dataPartenza = new JLabel("Data partenza:");
                    dataPartenza.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataPartenza.setForeground(new Color(70, 85, 105));
                    dataPartenza.setBounds(0, 55, 140, 30);

                    fieldDataPartenza = new JCalendarComboBox();
                    fieldDataPartenza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataPartenza.setBounds(145, 55, 260, 30);

                    JLabel dataRitorno = new JLabel("Data ritorno:");
                    dataRitorno.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataRitorno.setForeground(new Color(70, 85, 105));
                    dataRitorno.setBounds(0, 100, 140, 30);

                    fieldDataRitorno = new JCalendarComboBox();
                    fieldDataRitorno.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataRitorno.setBounds(145, 100, 260, 30);

                    panelSpecifico.add(destinazione);
                    panelSpecifico.add(fieldDestinazione);
                    panelSpecifico.add(dataPartenza);
                    panelSpecifico.add(fieldDataPartenza);
                    panelSpecifico.add(dataRitorno);
                    panelSpecifico.add(fieldDataRitorno);
                }

                if (categoria.equals("COINQUILINI")) {
                    JLabel provincia = new JLabel("Provincia:");
                    provincia.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    provincia.setForeground(new Color(70, 85, 105));
                    provincia.setBounds(0, 10, 140, 30);

                    fieldProvincia = new JTextField();
                    fieldProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldProvincia.setBounds(145, 10, 260, 30);

                    JLabel citta = new JLabel("Città:");
                    citta.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    citta.setForeground(new Color(70, 85, 105));
                    citta.setBounds(0, 55, 140, 30);

                    fieldCitta = new JTextField();
                    fieldCitta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldCitta.setBounds(145, 55, 260, 30);

                    JLabel via = new JLabel("Via:");
                    via.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    via.setForeground(new Color(70, 85, 105));
                    via.setBounds(0, 100, 140, 30);

                    fieldVia = new JTextField();
                    fieldVia.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldVia.setBounds(145, 100, 260, 30);

                    JLabel numeroCivico = new JLabel("Numero Civico:");
                    numeroCivico.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    numeroCivico.setForeground(new Color(70, 85, 105));
                    numeroCivico.setBounds(0, 145, 140, 30);

                    fieldNumeroCivico = new JTextField();
                    fieldNumeroCivico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldNumeroCivico.setBounds(145, 145, 260, 30);

                    panelSpecifico.add(provincia);
                    panelSpecifico.add(fieldProvincia);
                    panelSpecifico.add(citta);
                    panelSpecifico.add(fieldCitta);
                    panelSpecifico.add(via);
                    panelSpecifico.add(fieldVia);
                    panelSpecifico.add(numeroCivico);
                    panelSpecifico.add(fieldNumeroCivico);
                }

                if (categoria.equals("STUDIO")) {
                    JLabel esame = new JLabel("Nome Esame:");
                    esame.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    esame.setForeground(new Color(70, 85, 105));
                    esame.setBounds(0, 10, 140, 30);

                    fieldEsame = new JTextField();
                    fieldEsame.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldEsame.setBounds(145, 10, 260, 30);

                    JLabel dataAppello = new JLabel("Data Appello:");
                    dataAppello.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    dataAppello.setForeground(new Color(70, 85, 105));
                    dataAppello.setBounds(0, 55, 140, 30);

                    fieldDataAppello = new JCalendarComboBox();
                    fieldDataAppello.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    fieldDataAppello.setBounds(145, 55, 260, 30);

                    panelSpecifico.add(dataAppello);
                    panelSpecifico.add(fieldDataAppello);
                    panelSpecifico.add(esame);
                    panelSpecifico.add(fieldEsame);
                }

                panelSpecifico.revalidate();
                panelSpecifico.repaint();
            }
        });

        confermaCreazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = scriviNome.getText().trim();
                String categoria = scegliCategoria.getSelectedItem().toString();

                if (nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Inserire il nome del gruppo");
                    return;
                }

                switch (categoria) {
                    case "GENERICO":
                        controller.btn_creazioneGruppo_generico(nome);
                        break;
                    case "VIAGGIO":
                        String destinazione = fieldDestinazione.getText();
                        LocalDate dataPartenza = fieldDataPartenza.getDate().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate dataRitorno = fieldDataRitorno.getDate().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        if (destinazione.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Compila tutti i campi del viaggio");
                            return;
                        }
                        controller.btn_creazioneGruppo_viaggio(nome, destinazione, dataPartenza, dataRitorno);
                        break;
                    case "COINQUILINI":
                        String provincia = fieldProvincia.getText();
                        String citta = fieldCitta.getText();
                        String via = fieldVia.getText();
                        String numCiv = fieldNumeroCivico.getText();
                        if (provincia.isEmpty() || citta.isEmpty() || via.isEmpty() || numCiv.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Compila tutti i campi dell'indirizzo");
                            return;
                        }
                        controller.btn_creazioneGruppo_coinquilini(nome, provincia, citta, via, numCiv);
                        break;
                    case "STUDIO":
                        String nomeEsame = fieldEsame.getText();
                        LocalDate dataAppello = fieldDataAppello.getDate().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate();
                        if (nomeEsame.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Compila tutti i campi dello studio");
                            return;
                        }
                        controller.btn_creazioneGruppo_studio(nome, nomeEsame, dataAppello);
                        break;
                }
            }
        });

        annulla.addActionListener(e -> controller.btn_creazioneGruppo_annulla());
        tornaHome.addActionListener(e -> controller.btn_creazioneGruppo_tornaHome());

        setContentPane(panel);
        setSize(600, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
