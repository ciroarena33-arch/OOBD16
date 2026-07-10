package gui.scadenze;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import control.ScadenzeController;

public class Scadenze extends JFrame {

	private static final long serialVersionUID = 1L;
	private ScadenzeController controller;

	private JPanel contentPane;
	private JTable table;
	private JLabel lblTitolo;
	private JScrollPane scrollPane;
	private JButton btnAggiungi;
	private JButton btnModifica;
	private JButton btnIndietro;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scadenze frame = new Scadenze(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Scadenze(ScadenzeController controller) {
		this.controller = controller;

		setTitle("Scadenze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(430, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitolo = new JLabel("Lista Scadenze");
		lblTitolo.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitolo.setBounds(130, 10, 160, 30);
		contentPane.add(lblTitolo);
		
		String[] colonne= {
				"Nome", "Data di Scadenza", "Importo"
		};
		String[][] tuple={
				{"Ciro", "21/12/2112", "21,43"},
				{"Ciro", "21/12/2112", "21,43"},

		};
		
		table = new JTable(tuple, colonne);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(84, 44, 257, 157);
		contentPane.add(scrollPane);
		
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(84, 230, 88, 22);
		contentPane.add(btnAggiungi);
		
		btnModifica = new JButton("Modifica");
		btnModifica.setBounds(176, 230, 88, 22);
		contentPane.add(btnModifica);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(268, 230, 88, 22);
		contentPane.add(btnIndietro);

	}
}
