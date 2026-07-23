package gui.gruppo;

import javax.swing.*;

import control.GruppoController;
import de.wannawork.jcalendar.JCalendarComboBox;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

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
    private JTextField fieldNomeEsame;
    JCalendarComboBox fieldDataAppello;

    public CreazioneGruppoGUI(GruppoController controller) {
        super("Creazione gruppo");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titolo = new JLabel("Crea nuovo gruppo", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 45));
        titolo.setBounds(200, 45, 600, 70);

        JLabel nomeGruppo = new JLabel("Nome gruppo:");
        nomeGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        nomeGruppo.setBounds(315, 145, 180, 30);

        JTextField scriviNome = new JTextField();
        scriviNome.setFont(new Font("Arial", Font.PLAIN, 18));
        scriviNome.setBounds(455, 145, 260, 35);

        JLabel categoriaGruppo = new JLabel("Tipologia:");
        categoriaGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        categoriaGruppo.setBounds(315, 200, 180, 30);

        String[] tipiGruppo = {"GENERICO", "VIAGGIO", "COINQUILINI", "STUDIO"};
        JComboBox<String> scegliCategoria = new JComboBox<>(tipiGruppo);
        scegliCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
        scegliCategoria.setBounds(455, 200, 260, 35);

        JPanel panelSpecifico = new JPanel();
        panelSpecifico.setLayout(null);
        panelSpecifico.setBounds(315, 260, 400, 202);

        JButton confermaCreazione = new JButton("CONFERMA CREAZIONE");
        confermaCreazione.setFont(new Font("Arial", Font.BOLD, 16));
        confermaCreazione.setBounds(315, 473, 400, 40);

        JButton annulla = new JButton("ANNULLA");
        annulla.setFont(new Font("Arial", Font.BOLD, 16));
        annulla.setBounds(315, 533, 190, 40);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 16));
        tornaHome.setBounds(525, 533, 190, 40);

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(200, 550, 600, 35); 
        
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
                    destinazione.setFont(new Font("Arial", Font.BOLD, 15));
                    destinazione.setBounds(0, 10, 140, 30);

                    JTextField fieldDestinazione = new JTextField();
                    fieldDestinazione.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDestinazione.setBounds(140, 10, 260, 32);

                    JLabel dataPartenza = new JLabel("Data partenza:");
                    dataPartenza.setFont(new Font("Arial", Font.BOLD, 15));
                    dataPartenza.setBounds(0, 60, 140, 30);

                    JCalendarComboBox fieldDataPartenza = new JCalendarComboBox();
                    fieldDataPartenza.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataPartenza.setBounds(140, 60, 260, 32);
                    
                    JLabel dataRitorno = new JLabel("Data ritorno:");
                    dataRitorno.setFont(new Font("Arial", Font.BOLD, 15));
                    dataRitorno.setBounds(0, 110, 140, 30);

                    JCalendarComboBox fieldDataRitorno = new JCalendarComboBox();
                    fieldDataRitorno.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataRitorno.setBounds(140, 110, 260, 32);

                    panelSpecifico.add(destinazione);
                    panelSpecifico.add(fieldDestinazione);
                    panelSpecifico.add(dataPartenza);
                    panelSpecifico.add(fieldDataPartenza);
                    panelSpecifico.add(dataRitorno);
                    panelSpecifico.add(fieldDataRitorno);
                }

                if (categoria.equals("COINQUILINI")) {
                    JLabel provincia = new JLabel("Provincia:");
                    provincia.setFont(new Font("Arial", Font.BOLD, 15));
                    provincia.setBounds(0, 10, 140, 30);

                    JTextField fieldProvincia = new JTextField();
                    fieldProvincia.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldProvincia.setBounds(140, 10, 260, 32);

                    JLabel citta = new JLabel("Città:");
                    citta.setFont(new Font("Arial", Font.BOLD, 15));
                    citta.setBounds(0, 60, 140, 30);

                    JTextField fieldCitta = new JTextField();
                    fieldCitta.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldCitta.setBounds(140, 60, 260, 32);

                    JLabel via = new JLabel("Via:");
                    via.setFont(new Font("Arial", Font.BOLD, 15));
                    via.setBounds(0, 110, 140, 30);

                    JTextField fieldVia = new JTextField();
                    fieldVia.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldVia.setBounds(140, 110, 260, 32);

                    JLabel numeroCivico = new JLabel("Numero Civico:");
                    numeroCivico.setFont(new Font("Arial", Font.BOLD, 15));
                    numeroCivico.setBounds(0, 160, 140, 30);

                    JTextField fieldNumeroCivico = new JTextField();
                    fieldNumeroCivico.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldNumeroCivico.setBounds(140, 160, 260, 32);

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
                    esame.setFont(new Font("Arial", Font.BOLD, 15));
                    esame.setBounds(0, 10, 140, 30);

                    JTextField fieldEsame = new JTextField();
                    fieldEsame.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldEsame.setBounds(140, 10, 260, 32);
                    
                    JLabel dataAppello = new JLabel("data Appello:");
                    dataAppello.setFont(new Font("Arial", Font.BOLD, 15));
                    dataAppello.setBounds(0, 60, 140, 30);

                    JCalendarComboBox fieldDataAppello = new JCalendarComboBox();
                    fieldDataAppello.setFont(new Font("Arial", Font.PLAIN, 16));
                    fieldDataAppello.setBounds(140, 60, 260, 32);
                    
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
                    	
                    	String destinazione=fieldDestinazione.getText();
                    	String dataPartenza=fieldDataPartenza.getDate().toString();
                    	String dataRitorno=fieldDataRitorno.getDate().toString();
                    	
                        if (destinazione.isEmpty()||dataPartenza.isEmpty()||dataRitorno.isEmpty()) {
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
                        String nomeEsame = fieldNomeEsame.getText();
                        String dataAppello = fieldDataAppello.getDate().toString();
                        
                        if (nomeEsame.isEmpty() || dataAppello.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Compila tutti i campi dello studio");
                            return;
                        }
                        
                        controller.btn_creazioneGruppo_studio(nome, nomeEsame, dataAppello);
                        break;
                }
            }
        });

        annulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_creazioneGruppo_annulla();
            }
        });

        tornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_creazioneGruppo_tornaHome();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}