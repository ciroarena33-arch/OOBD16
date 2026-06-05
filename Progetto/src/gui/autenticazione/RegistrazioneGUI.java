package gui.autenticazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Registrazione");

        JPanel panelPrincipale = new JPanel();
        JPanel panel = new JPanel();

        panelPrincipale.setLayout(new GridBagLayout());

        panel.setPreferredSize(new Dimension(700, 650));
        panel.setLayout(new GridLayout(12, 1, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel logo = new JLabel("UninaMoneySplit", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 45));

        JLabel nome = new JLabel("Nome");
        nome.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField inserisciNome = new JTextField();
        inserisciNome.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel cognome = new JLabel("Cognome");
        cognome.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField inserisciCognome = new JTextField();
        inserisciCognome.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel email = new JLabel("Email istituzionale");
        email.setFont(new Font("Arial", Font.BOLD, 16));

        JTextField inserisciEmail = new JTextField();
        inserisciEmail.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 16));

        JPasswordField inserisciPassword = new JPasswordField();
        inserisciPassword.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton registrati = new JButton("REGISTRATI");
        registrati.setFont(new Font("Arial", Font.BOLD, 16));

        JButton tornaLogin = new JButton("TORNA AL LOGIN");
        tornaLogin.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel messaggio = new JLabel("", SwingConstants.CENTER);
        messaggio.setFont(new Font("Arial", Font.BOLD, 15));

        panel.add(logo);

        panel.add(nome);
        panel.add(inserisciNome);

        panel.add(cognome);
        panel.add(inserisciCognome);

        panel.add(email);
        panel.add(inserisciEmail);

        panel.add(password);
        panel.add(inserisciPassword);

        panel.add(registrati);
        panel.add(tornaLogin);
        panel.add(messaggio);

        panelPrincipale.add(panel);

        registrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome1 = inserisciNome.getText();
                String cognome1 = inserisciCognome.getText();
                String email1 = inserisciEmail.getText();
                String password1 = inserisciPassword.getText();

                if (nome1.isEmpty() || cognome1.isEmpty() || email1.isEmpty() || password1.isEmpty()) {
                    messaggio.setText("Compila tutti i campi");
                } else {
                    messaggio.setText("Registrazione effettuata");
                    frame.dispose();
                    HomeGUI.main(null);
                }
            }
        });

        tornaLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginGUI.main(null);
            }
        });

        frame.setContentPane(panelPrincipale);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null); // facciamo partire a centro schermo
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}