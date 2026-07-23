package gui.scadenze;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import control.ScadenzeController;
import de.wannawork.jcalendar.JCalendarComboBox;

public class DettaglioScadenzaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private ScadenzeController controller;
	private JPanel contentPane;
	private JTextField textField;
	private JCalendarComboBox textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JButton btnNewButton;
	private JButton btnCancella;
	private JButton btnIndietro;
	private JLabel lblNewLabel_2;

	

	public DettaglioScadenzaGUI(ScadenzeController controller, String nome, LocalDate data, String importo) {
		this(controller);
		textField.setText(nome);
		textField_1.setDate(Date.valueOf(data));
		textField_2.setText(importo);
	}

	public DettaglioScadenzaGUI(ScadenzeController controller) {
		this.controller = controller;

		setTitle("Dettaglio Scadenza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(430, 290);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(83, 72, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Data di Scadenza");
		lblNewLabel_1.setBounds(83, 121, 100, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("Importo");
		lblNewLabel_1_1.setBounds(83, 172, 100, 14);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField(controller.getScadenza().getNome());
		textField.setBounds(204, 69, 120, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JCalendarComboBox();
		textField_1.setDate(Date.valueOf(controller.getScadenza().getDataScadenza()));
		textField_1.setBounds(204, 118, 120, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField(String.valueOf(controller.getScadenza().getImporto()));
		textField_2.setBounds(204, 169, 120, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("Salva");
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.btn_dettaglioScadenza_salva(textField.getText(), textField_1.getDate().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate(),
						textField_2.getText());
			}
		});
		btnNewButton.setBounds(60, 214, 89, 23);
		contentPane.add(btnNewButton);
		
		btnCancella = new JButton("Cancella");
		btnCancella.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.btn_dettaglioScadenza_cancella();
			}
		});
		btnCancella.setBounds(160, 214, 89, 23);
		contentPane.add(btnCancella);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				controller.btn_dettaglioScadenza_indietro();
			}
		});
		btnIndietro.setBounds(259, 214, 89, 23);
		contentPane.add(btnIndietro);
		
		lblNewLabel_2 = new JLabel("Dettaglio scadenza");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(83, 15, 239, 35);
		contentPane.add(lblNewLabel_2);

	}
}
