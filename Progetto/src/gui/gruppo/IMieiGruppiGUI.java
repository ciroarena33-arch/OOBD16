package gui.gruppo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import control.GruppoController;
import model.PartecipazioneGruppo;

public class IMieiGruppiGUI extends JFrame {

    private GruppoController controller;
    private JList<PartecipazioneGruppo> listaGruppi;

    public IMieiGruppiGUI(GruppoController controller) {
        super();
        setTitle("I miei gruppi");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 250));
        panel.setLayout(null);

        JLabel titolo = new JLabel("I miei gruppi", SwingConstants.CENTER);
        titolo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titolo.setForeground(new Color(30, 41, 59));
        titolo.setBounds(0, 20, 700, 36);

        listaGruppi = new JList<>();
        listaGruppi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        listaGruppi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaGruppi.setFixedCellHeight(36);
        listaGruppi.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listaGruppi);
        scrollPane.setBounds(40, 70, 620, 330);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));

        JButton apriGruppo = new JButton("APRI GRUPPO");
        apriGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        apriGruppo.setBackground(new Color(60, 120, 216));
        apriGruppo.setForeground(Color.WHITE);
        apriGruppo.setFocusPainted(false);
        apriGruppo.setBounds(40, 420, 190, 38);

        JButton creaGruppo = new JButton("CREA GRUPPO");
        creaGruppo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        creaGruppo.setBackground(new Color(60, 120, 216));
        creaGruppo.setForeground(Color.WHITE);
        creaGruppo.setFocusPainted(false);
        creaGruppo.setBounds(255, 420, 190, 38);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tornaHome.setBackground(new Color(110, 120, 135));
        tornaHome.setForeground(Color.WHITE);
        tornaHome.setFocusPainted(false);
        tornaHome.setBounds(470, 420, 190, 38);

        JLabel messaggio = new JLabel("Seleziona un gruppo per proseguire", SwingConstants.CENTER);
        messaggio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messaggio.setForeground(new Color(100, 116, 139));
        messaggio.setBounds(0, 470, 700, 25);

        panel.add(titolo);
        panel.add(scrollPane);
        panel.add(apriGruppo);
        panel.add(creaGruppo);
        panel.add(tornaHome);
        panel.add(messaggio);

        apriGruppo.addActionListener(e -> {
            PartecipazioneGruppo gruppoSelezionato = listaGruppi.getSelectedValue(); //restituisce oggetto selezionato nella Jlist
            if (gruppoSelezionato == null) {
                JOptionPane.showMessageDialog(IMieiGruppiGUI.this, "Seleziona prima un gruppo");
            } else {
                controller.btn_iMieiGruppi_apriGruppo(gruppoSelezionato);
            }
        });

        creaGruppo.addActionListener(e -> controller.btn_iMieiGruppi_creaGruppo());
        tornaHome.addActionListener(e -> controller.btn_iMieiGruppi_tornaHome());

        setContentPane(panel);
        setSize(715, 540);
        setResizable(false); //impedisce all'utente di ridimensionare la finestra
        setLocationRelativeTo(null); //centra la finestra sullo schermo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //quando chiudi la finestra termina l'applicazione
    }

    public void aggiornaJList(DefaultListModel<PartecipazioneGruppo> model) { //aggiorna elementi visualizzati nella lista grafica
        listaGruppi.setModel(model);
    }
}