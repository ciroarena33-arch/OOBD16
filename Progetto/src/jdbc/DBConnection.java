package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dbconn = null;
    private Connection conn = null;
    private static final File PWD_FILE = new File("pwdfile");

    private DBConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException ignored) {
            }
        }
    }

    public static synchronized DBConnection getDBConnection() {
        if (dbconn == null) {
            dbconn = new DBConnection();
        }
        return dbconn;
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                String[] credenziali = leggiCredenziali();
                conn = DriverManager.getConnection(credenziali[0], credenziali[1], credenziali[2]);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Errore di connessione al Database: " + throwables.getMessage(), throwables);
        }
        return conn;
    }

    private String[] leggiCredenziali() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PWD_FILE))) {
            String url = reader.readLine();
            String utente = reader.readLine();
            String password = reader.readLine();
            if (url == null || utente == null || password == null
                    || url.isBlank() || utente.isBlank() || password.isBlank()) {
                throw new RuntimeException("Il file pwdfile deve contenere URL, utente e password su tre righe.");
            }
            return new String[]{url.trim(), utente.trim(), password};
        } catch (IOException e) {
            throw new RuntimeException("Impossibile leggere il file pwdfile: " + e.getMessage(), e);
        }
    }
}
