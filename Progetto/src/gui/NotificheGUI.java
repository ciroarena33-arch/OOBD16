package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class NotificheGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificheGUI frame = new NotificheGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotificheGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNotInviate=new JButton("Notifiche Inviate");
		JButton btnNotRicevute=new JButton("Notifiche Ricevute");
		JButton btnRichieste=new JButton("Notifiche Inviti");		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(50, 0, 330, 20);
		menuBar.add(btnRichieste);
		menuBar.add(btnNotInviate);
		menuBar.add(btnNotRicevute);
		
		contentPane.add(menuBar);
		
		JButton btnNewButton = new JButton("CreaNotifica");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(174, 230, 98, 22);
		contentPane.add(btnNewButton);
		
		table = new JTable();
		table.setBounds(60, 41, 320, 178);
		contentPane.add(table);

	}
}
