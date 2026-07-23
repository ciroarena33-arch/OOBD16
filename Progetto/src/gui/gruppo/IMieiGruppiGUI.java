package gui.gruppo;

import javax.swing.*; 

import control.GruppoController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IMieiGruppiGUI extends JFrame {

    private GruppoController controller;
    private JList<Object> listaGruppi;

    public IMieiGruppiGUI(GruppoController controller) {
        super("I miei gruppi");
        this.controller = controller;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titolo = new JLabel("I miei gruppi", SwingConstants.CENTER);
        titolo.setFont(new Font("Arial", Font.BOLD, 50));
        titolo.setBounds(250, 45, 500, 70);

        listaGruppi = new JList<>();
        listaGruppi.setFont(new Font("Arial", Font.PLAIN, 20));
        listaGruppi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaGruppi);
        scrollPane.setBounds(315, 150, 400, 260);

        JButton apriGruppo = new JButton("APRI GRUPPO");
        apriGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        apriGruppo.setBounds(315, 450, 190, 40);

        JButton creaGruppo = new JButton("CREA NUOVO GRUPPO");
        creaGruppo.setFont(new Font("Arial", Font.BOLD, 16));
        creaGruppo.setBounds(525, 450, 190, 40);

        JButton tornaHome = new JButton("TORNA HOME");
        tornaHome.setFont(new Font("Arial", Font.BOLD, 16));
        tornaHome.setBounds(315, 510, 400, 40);

        JLabel messaggio = new JLabel("Seleziona un gruppo", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));
        messaggio.setBounds(250, 565, 500, 35);

        panel.add(titolo);
        panel.add(scrollPane);
        panel.add(apriGruppo);
        panel.add(creaGruppo);
        panel.add(tornaHome);
        panel.add(messaggio);

        apriGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object gruppoSelezionato = listaGruppi.getSelectedValue();

                if (gruppoSelezionato == null) {
                    JOptionPane.showMessageDialog(IMieiGruppiGUI.this, "Seleziona prima un gruppo");
                } else {
                    controller.btn_iMieiGruppi_apriGruppo(gruppoSelezionato);
                }
            }
        });

        creaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_iMieiGruppi_creaGruppo();
            }
        });

        tornaHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_iMieiGruppi_tornaHome();
            }
        });

        setContentPane(panel);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public void aggiornaJList(DefaultListModel<Object> model) {
		listaGruppi.setModel(model);
	}
}