package gui.partecipanti;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import control.PartecipantiController;

public class InfoPartecipanteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private PartecipantiController controller;

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textEmail;
	private JTextField textTelefono;
	private JLabel lblNome;
	private JLabel lblCognome;
	private JLabel lblEmailIstituzionale;
	private JLabel lblNumTelefono;
	private JButton btnIndietro;
	private JButton btnNewProprietario;

	public InfoPartecipanteGUI(PartecipantiController controller) {
		this.controller = controller;

		setTitle("Dati Utente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(400, 320);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
		lblNome.setBounds(40, 45, 110, 22);
		contentPane.add(lblNome);
		
		lblCognome = new JLabel("Cognome:");
		lblCognome.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
		lblCognome.setBounds(40, 85, 110, 22);
		contentPane.add(lblCognome);
		
		lblEmailIstituzionale = new JLabel("Email:");
		lblEmailIstituzionale.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
		lblEmailIstituzionale.setBounds(40, 125, 110, 22);
		contentPane.add(lblEmailIstituzionale);
		
		lblNumTelefono = new JLabel("Telefono:");
		lblNumTelefono.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
		lblNumTelefono.setBounds(40, 165, 110, 22);
		contentPane.add(lblNumTelefono);
		
		textNome = new JTextField(controller.getPartecipanteSelezionato().getNome());
		textNome.setEditable(false);
		textNome.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textNome.setBounds(160, 43, 185, 25);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField(controller.getPartecipanteSelezionato().getCognome());
		textCognome.setEditable(false);
		textCognome.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textCognome.setBounds(160, 83, 185, 25);
		contentPane.add(textCognome);
		textCognome.setColumns(10);
		
		textEmail = new JTextField(controller.getPartecipanteSelezionato().getEmailIstituzionale());
		textEmail.setEditable(false);
		textEmail.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textEmail.setBounds(160, 123, 185, 25);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		String telefono=controller.getPartecipanteSelezionato().getTelefono();
		textTelefono = new JTextField(telefono.isEmpty()? telefono : "nessuno");
		textTelefono.setEditable(false);
		textTelefono.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textTelefono.setBounds(160, 163, 185, 25);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btn_infoPartecipante_indietro();
			}
		});
		btnIndietro.setBounds(220, 225, 130, 32);
		contentPane.add(btnIndietro);
		
		btnNewProprietario = new JButton("RENDI PROPRIETARIO");
		btnNewProprietario.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
		btnNewProprietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btn_infoPartecipante_rendiProprietario();
			}
		});
		if(controller.getUtenteLoggato().getEmailIstituzionale().equals(controller.getGruppoSelezionato().getProprietario().getEmailIstituzionale())) {
			btnIndietro.setBounds(220, 225, 130, 32);
			contentPane.add(btnIndietro);
			btnNewProprietario.setBounds(50, 225, 160, 32);
			contentPane.add(btnNewProprietario);
		}
		else {
			btnIndietro.setBounds(135, 225, 130, 32);
			contentPane.add(btnIndietro);
		}
		

	}
}
