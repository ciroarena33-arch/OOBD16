package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SpesaUtenteGruppo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpesaUtenteGruppo frame = new SpesaUtenteGruppo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public SpesaUtenteGruppo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitolo = new JLabel("DettaglioSpesa");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitolo.setBounds(150, 11, 122, 17);
		contentPane.add(lblTitolo);
		
		JLabel lblNomeSpesa = new JLabel("Nome Spesa");
		lblNomeSpesa.setBounds(110, 39, 73, 14);
		contentPane.add(lblNomeSpesa);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(110, 64, 73, 14);
		contentPane.add(lblDescrizione);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(193, 59, 179, 88);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(193, 36, 179, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblImporto = new JLabel("Importo");
		lblImporto.setBounds(110, 158, 48, 14);
		contentPane.add(lblImporto);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(193, 155, 179, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDestinatario = new JLabel("Destinatario");
		lblDestinatario.setBounds(110, 187, 73, 14);
		contentPane.add(lblDestinatario);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(193, 184, 179, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnPagaDebito = new JButton("Paga Spesa");
		btnPagaDebito.setBounds(110, 230, 104, 22);
		contentPane.add(btnPagaDebito);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setBounds(258, 230, 104, 22);
		contentPane.add(btnIndietro);

	}
}
