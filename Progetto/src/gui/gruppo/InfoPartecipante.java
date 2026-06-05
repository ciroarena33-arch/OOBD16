package gui.gruppo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoPartecipante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCognome;
	private JTextField textEmail;
	private JTextField textTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoPartecipante frame = new InfoPartecipante();
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
	public InfoPartecipante() {
		setTitle("Dati Utente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(57, 46, 93, 14);
		contentPane.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(57, 83, 93, 14);
		contentPane.add(lblCognome);
		
		JLabel lblEmailIstituzionale = new JLabel("Email Istituzionale");
		lblEmailIstituzionale.setBounds(57, 119, 93, 14);
		contentPane.add(lblEmailIstituzionale);
		
		JLabel lblNumTelefono = new JLabel("Num. telefono");
		lblNumTelefono.setBounds(57, 155, 93, 14);
		contentPane.add(lblNumTelefono);
		
		textNome = new JTextField();
		textNome.setBounds(178, 43, 86, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textCognome = new JTextField();
		textCognome.setBounds(178, 80, 86, 20);
		contentPane.add(textCognome);
		textCognome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(178, 116, 86, 20);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(178, 152, 86, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);
		
		JButton btnNewButton = new JButton("Indietro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(178, 201, 119, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewProprietario = new JButton("Rendi Proprietario");
		btnNewProprietario.setBounds(31, 201, 119, 23);
		contentPane.add(btnNewProprietario);

	}
}
