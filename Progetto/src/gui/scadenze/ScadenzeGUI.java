package gui.scadenze;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import control.ScadenzeController;

public class ScadenzeGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private ScadenzeController controller;

	private JPanel contentPane;

	private JTable table;
    private DefaultTableModel tableModel=new DefaultTableModel();
    
	private JLabel lblTitolo;
	private JScrollPane scrollPane;
	private JButton btnAggiungi;
	private JButton btnModifica;
	private JButton btnIndietro;

	public ScadenzeGUI(ScadenzeController controller) {
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
		
		tableModel=new DefaultTableModel(colonne,0);
		table = new JTable(tableModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(84, 44, 257, 157);
		contentPane.add(scrollPane);
				
		btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.btn_scadenze_aggiungi();
			}
		});
		btnAggiungi.setBounds(84, 230, 88, 22);
		contentPane.add(btnAggiungi);
		
		btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int modelRow=table.convertRowIndexToModel(row);
					Object scadenza=tableModel.getValueAt(modelRow, 0);
					controller.btn_scadenze_modifica(scadenza);
				} else {
					JOptionPane.showMessageDialog(ScadenzeGUI.this, "Seleziona prima una scadenza");
				}
			}
		});
		btnModifica.setBounds(176, 230, 88, 22);
		contentPane.add(btnModifica);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.btn_scadenze_indietro();
			}
		});
		btnIndietro.setBounds(268, 230, 88, 22);
		contentPane.add(btnIndietro);

	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	public JTable getTable(){
		return table;
	}
}
