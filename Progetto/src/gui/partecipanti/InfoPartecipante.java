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

public class InfoPartecipante extends JFrame {

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
	private JButton btnNewButton;
	private JButton btnNewProprietario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoPartecipante frame = new InfoPartecipante(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InfoPartecipante(PartecipantiController controller) {
		this.controller = controller;

		setTitle("Dati Utente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textNome = new JTextField();
		textNome.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textNome.setBounds(160, 43, 185, 25);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textCognome.setBounds(160, 83, 185, 25);
		contentPane.add(textCognome);
		textCognome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textEmail.setBounds(160, 123, 185, 25);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
		textTelefono.setBounds(160, 163, 185, 25);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		btnNewButton = new JButton("INDIETRO");
		btnNewButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(220, 225, 130, 32);
		contentPane.add(btnNewButton);
		
		btnNewProprietario = new JButton("RENDI PROPRIETARIO");
		btnNewProprietario.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
		btnNewProprietario.setBounds(50, 225, 160, 32);
		contentPane.add(btnNewProprietario);

	}
}
