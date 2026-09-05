package gui.spesa;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import control.SpesaController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class InserisciSpesaGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private SpesaController controller;
    private JPanel contentPane;
    private JLabel titolo, messaggio;
    private JLabel labelNome, labelDescrizione, labelImporto, labelData, labelTipo, labelValuta, labelPartecipanti;
    private JTextField fieldNome, fieldDescrizione, fieldImporto;
    private JCalendarComboBox fieldData;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboValuta;
    private JPanel panelPartecipanti;
    private JScrollPane scrollPartecipanti;
    private JButton registraSpesa, tornaGruppo;

    private Map<JCheckBox, Object> checkMap = new HashMap<>();

    public InserisciSpesaGUI(SpesaController controller) {
        super();
        setTitle("Inserisci Spesa");
        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(540, 640);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(245, 245, 250));
        setContentPane(contentPane);

        titolo = new JLabel("Inserisci nuova spesa", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 15, 540, 36);
        contentPane.add(titolo);

        int startY = 62;
        int stepY = 44;

        // Nome spesa
        labelNome = new JLabel("Nome spesa:");
        labelNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelNome.setForeground(new Color(70, 85, 105));
        labelNome.setBounds(35, startY, 110, 32);
        contentPane.add(labelNome);

        fieldNome = new JTextField();
        fieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldNome.setBounds(150, startY, 340, 32);
        contentPane.add(fieldNome);

        // Descrizione
        labelDescrizione = new JLabel("Descrizione:");
        labelDescrizione.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelDescrizione.setForeground(new Color(70, 85, 105));
        labelDescrizione.setBounds(35, startY + stepY, 110, 32);
        contentPane.add(labelDescrizione);

        fieldDescrizione = new JTextField();
        fieldDescrizione.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldDescrizione.setBounds(150, startY + stepY, 340, 32);
        contentPane.add(fieldDescrizione);

        // Importo
        labelImporto = new JLabel("Importo:");
        labelImporto.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelImporto.setForeground(new Color(70, 85, 105));
        labelImporto.setBounds(35, startY + stepY * 2, 110, 32);
        contentPane.add(labelImporto);

        fieldImporto = new JTextField();
        fieldImporto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldImporto.setBounds(150, startY + stepY * 2, 340, 32);
        contentPane.add(fieldImporto);

        // Data spesa
        labelData = new JLabel("Data spesa:");
        labelData.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelData.setForeground(new Color(70, 85, 105));
        labelData.setBounds(35, startY + stepY * 3, 110, 32);
        contentPane.add(labelData);

        fieldData = new JCalendarComboBox();
        fieldData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldData.setBounds(150, startY + stepY * 3, 340, 32);
        contentPane.add(fieldData);

        labelTipo = new JLabel("Tipo spesa:");
        labelTipo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelTipo.setForeground(new Color(70, 85, 105));
        labelTipo.setBounds(35, startY + stepY * 4, 110, 32);
        contentPane.add(labelTipo);

        String[] tipiSpesa = {"COMUNE", "PERSONALE"};
        comboTipo = new JComboBox<>(tipiSpesa);
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboTipo.setBounds(150, startY + stepY * 4, 340, 32);
        comboTipo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                boolean isComune = "COMUNE".equalsIgnoreCase(e.getItem().toString());
                impostaVisibilitaPartecipanti(isComune);
            }
        });
        contentPane.add(comboTipo);

        labelValuta = new JLabel("Valuta:");
        labelValuta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelValuta.setForeground(new Color(70, 85, 105));
        labelValuta.setBounds(35, startY + stepY * 5, 110, 32);
        contentPane.add(labelValuta);

        comboValuta = new JComboBox<>();
        comboValuta.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboValuta.setBounds(150, startY + stepY * 5, 340, 32);
        contentPane.add(comboValuta);

        labelPartecipanti = new JLabel("Dividi con:");
        labelPartecipanti.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelPartecipanti.setForeground(new Color(70, 85, 105));
        labelPartecipanti.setBounds(35, startY + stepY * 6, 110, 32);
        contentPane.add(labelPartecipanti);

        panelPartecipanti = new JPanel();
        panelPartecipanti.setLayout(new BoxLayout(panelPartecipanti, BoxLayout.Y_AXIS));
        panelPartecipanti.setBackground(Color.WHITE);

        scrollPartecipanti = new JScrollPane(panelPartecipanti);
        scrollPartecipanti.setBounds(150, startY + stepY * 6, 340, 110);
        scrollPartecipanti.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));
        contentPane.add(scrollPartecipanti);

        // Pulsanti Azione
        int btnY = 525;
        registraSpesa = new JButton("REGISTRA SPESA");
        registraSpesa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        registraSpesa.setBackground(new Color(60, 120, 216));
        registraSpesa.setForeground(Color.WHITE);
        registraSpesa.setFocusPainted(false);
        registraSpesa.setBounds(35, btnY, 220, 40);
        contentPane.add(registraSpesa);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaGruppo.setBackground(new Color(110, 120, 135));
        tornaGruppo.setForeground(Color.WHITE);
        tornaGruppo.setFocusPainted(false);
        tornaGruppo.setBounds(270, btnY, 220, 40);
        contentPane.add(tornaGruppo);

        messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setBounds(35, 572, 455, 25);
        contentPane.add(messaggio);

        registraSpesa.addActionListener(e -> {
            String nome = fieldNome.getText().trim();
            String descrizione = fieldDescrizione.getText().trim();
            String importo = fieldImporto.getText().trim();
            String tipo = comboTipo.getSelectedItem().toString();
            String valuta = comboValuta.getSelectedItem() != null ? comboValuta.getSelectedItem().toString() : "EUR";

            if (nome.isEmpty() || importo.isEmpty() || fieldData.getDate() == null) {
                messaggio.setText("Compila nome spesa, importo e data");
            } else {
                LocalDate data = fieldData.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                controller.btn_inserisciSpesa_registraSpesa(nome, descrizione, importo, valuta, data, tipo);
            }
        });

        tornaGruppo.addActionListener(e -> controller.btn_inserisciSpesa_tornaGruppo());
    }

    public void aggiornaValute(String[] valute) {
        comboValuta.removeAllItems();
        if (valute != null) {
            for (String v : valute) {
                comboValuta.addItem(v);
            }
        }
    }

    public void aggiornaPartecipanti(Object[] partecipanti) {
        panelPartecipanti.removeAll();
        checkMap.clear();
        if (partecipanti != null) {
            for (Object obj : partecipanti) {
                JCheckBox cb = new JCheckBox(obj.toString());
                cb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                cb.setBackground(Color.WHITE);
                cb.setSelected(true); // Default to true (checked) for all participants
                panelPartecipanti.add(cb);
                checkMap.put(cb, obj);
            }
        }
        panelPartecipanti.revalidate();
        panelPartecipanti.repaint();
    }

    public void impostaVisibilitaPartecipanti(boolean visibile) {
        labelPartecipanti.setVisible(visibile);
        scrollPartecipanti.setVisible(visibile);
    }

    public ArrayList<Object> getPartecipantiSelezionati() {
        ArrayList<Object> selezionati = new ArrayList<>();
        for (Map.Entry<JCheckBox, Object> entry : checkMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                selezionati.add(entry.getValue());
            }
        }
        return selezionati;
    }
}