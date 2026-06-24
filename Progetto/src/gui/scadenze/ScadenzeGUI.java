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

public class ScadenzeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScadenzeGUI frame = new ScadenzeGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScadenzeGUI() {
		setTitle("Scadenze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Lista Scadenze");
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitolo.setBounds(139, 11, 136, 25);
		contentPane.add(lblTitolo);
		
		String[] colonne= {
				"Nome", "Data di Scadenza", "Importo"
		};
		String[][] tuple={
				{"Ciro", "21/12/2112", "21,43"},
				{"Ciro", "21/12/2112", "21,43"},

		};
		
		
		table = new JTable(tuple, colonne);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(84, 44, 257, 157);
		contentPane.add(scrollPane);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.setBounds(84, 230, 88, 22);
		contentPane.add(btnAggiungi);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setBounds(176, 230, 88, 22);
		contentPane.add(btnModifica);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(268, 230, 88, 22);
		contentPane.add(btnIndietro);

	}

}
