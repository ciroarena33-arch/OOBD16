package gui.partecipanti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.PartecipantiController;
import gui.gruppo.DettagliGruppoGUI;

public class VisualizzaPartecipantiGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private PartecipantiController controller;

    private JPanel contentPane;
    private JList<Object> listaUtenti;
    private JScrollPane scrollPane;
    private JButton btnAggiungiPartecipante;
    private JButton vediDettagli;
    private JButton tornaGruppo;
   

    public VisualizzaPartecipantiGUI(PartecipantiController controller) {
        this.controller = controller;

        setTitle("Partecipanti gruppo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550, 520);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        listaUtenti = new JList<>();
        listaUtenti.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane = new JScrollPane(listaUtenti);
        scrollPane.setBounds(50, 80, 450, 250);
        contentPane.add(scrollPane);

        btnAggiungiPartecipante = new JButton("AGGIUNGI");
        btnAggiungiPartecipante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_visualizzaPartecipanti_aggiungiPartecipante();
            }
        });
        btnAggiungiPartecipante.setFont(new Font("Arial", Font.BOLD, 13));
        btnAggiungiPartecipante.setBounds(50, 360, 140, 35);
        contentPane.add(btnAggiungiPartecipante);

        vediDettagli = new JButton("VEDI DETTAGLI");
        vediDettagli.setFont(new Font("Arial", Font.BOLD, 13));
        vediDettagli.setBounds(205, 360, 140, 35);
        contentPane.add(vediDettagli);

        tornaGruppo = new JButton("TORNA AL GRUPPO");
        tornaGruppo.setFont(new Font("Arial", Font.BOLD, 13));
        tornaGruppo.setBounds(360, 360, 140, 35);
        contentPane.add(tornaGruppo);

        vediDettagli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Object utenteSelezionato=listaUtenti.getSelectedValue();
            	if(utenteSelezionato==null) {
            		JOptionPane.showMessageDialog(null, "Nessun partecipante selezionato", "Errore", 0);
            	}
            	else {
            		controller.btn_visualizzaPartecipanti_vediDettagli(utenteSelezionato);
            	}
            	
            }
        });

        tornaGruppo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.btn_visualizzaPartecipanti_tornaGruppo();
            }
        });
    }
    
	public void aggiornaJList(DefaultListModel<Object> model) {
		listaUtenti.setModel(model);
		JLabel numeroPartecipanti = new JLabel("Numero Partecipanti: " + (model.getSize() == 0 ? "nessuno" : model.getSize()+1),SwingConstants.CENTER);
        numeroPartecipanti.setFont(new Font("Arial", Font.BOLD, 16));
        numeroPartecipanti.setBounds(24, 37, 500, 30);
        contentPane.add(numeroPartecipanti);
	}
	
}