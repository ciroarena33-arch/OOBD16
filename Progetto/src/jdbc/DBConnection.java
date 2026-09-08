package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {

    private static DBConnection dbconn = null;
    private Connection conn = null;
    private Driver driverRegistrato = null;
    private static final File PWD_FILE = new File("pwdfile.txt");

    private DBConnection() {
        caricaDriver();
    }

    private void caricaDriver() {
        String[] driverClassNames = {"oracle.jdbc.OracleDriver", "oracle.jdbc.driver.OracleDriver"};

        // 1. Prova prima dal classpath standard del ClassLoader
        for (String className : driverClassNames) {
            try {
                Class<?> clazz = Class.forName(className);
                driverRegistrato = (Driver) clazz.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(new DriverShim(driverRegistrato));
                return;
            } catch (Throwable ignored) {
            }
        }

        // 2. Se non è nel classpath standard (es. Eclipse non ha ancora ricaricato le librerie),
        // cerca ojdbc*.jar nelle cartelle lib e caricalo a runtime
        List<File> candidatiJar = new ArrayList<>();
        String[] nomiJar = {"ojdbc17.jar", "ojdbc11.jar", "ojdbc8.jar", "ojdbc.jar"};

        for (String nome : nomiJar) {
            candidatiJar.add(new File("lib", nome));
            candidatiJar.add(new File("Progetto/lib", nome));
            candidatiJar.add(new File("..", "lib/" + nome));
            candidatiJar.add(new File("../Progetto/lib", nome));
        }

        try {
            URL location = DBConnection.class.getProtectionDomain().getCodeSource().getLocation();
            if (location != null) {
                File dir = new File(location.toURI());
                if (!dir.isDirectory()) {
                    dir = dir.getParentFile();
                }
                while (dir != null) {
                    for (String nome : nomiJar) {
                        candidatiJar.add(new File(dir, "lib/" + nome));
                        candidatiJar.add(new File(dir, "Progetto/lib/" + nome));
                    }
                    dir = dir.getParentFile();
                }
            }
        } catch (Exception ignored) {
        }

        for (File jar : candidatiJar) {
            if (jar.exists() && jar.isFile() && jar.canRead()) {
                try {
                    URLClassLoader loader = new URLClassLoader(
                            new URL[]{jar.toURI().toURL()},
                            DBConnection.class.getClassLoader()
                    );
                    for (String className : driverClassNames) {
                        try {
                            Class<?> clazz = Class.forName(className, true, loader);
                            driverRegistrato = (Driver) clazz.getDeclaredConstructor().newInstance();
                            DriverManager.registerDriver(new DriverShim(driverRegistrato));
                            return;
                        } catch (Throwable ignored) {
                        }
                    }
                } catch (Throwable ignored) {
                }
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
                try {
                    conn = DriverManager.getConnection(credenziali[0], credenziali[1], credenziali[2]);
                } catch (SQLException sqle) {
                    // Fallback diretto tramite l'istanza di Driver caricata
                    if (driverRegistrato != null) {
                        Properties props = new Properties();
                        props.put("user", credenziali[1]);
                        props.put("password", credenziali[2]);
                        conn = driverRegistrato.connect(credenziali[0], props);
                    }
                    if (conn == null) {
                        throw sqle;
                    }
                }
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Errore di connessione al Database: " + throwables.getMessage(), throwables);
        }
        return conn;
    }

    private String[] leggiCredenziali() {
        try (BufferedReader reader = apriReaderCredenziali()) {
            String url = reader.readLine();
            String utente = reader.readLine();
            String password = reader.readLine();
            if (url == null || utente == null || password == null
                    || url.isBlank() || utente.isBlank() || password.isBlank()) {
                throw new RuntimeException("Il file pwdfile deve contenere URL, utente e password su tre righe.");
            }
            url = url.replace("\uFEFF", "").trim();
            utente = utente.trim();
            password = password.trim();
            return new String[]{url, utente, password};
        } catch (IOException e) {
            throw new RuntimeException("Impossibile leggere il file pwdfile: " + e.getMessage(), e);
        }
    }

    private BufferedReader apriReaderCredenziali() throws IOException {
        // 1. Prova a cercarlo nel Classpath (es. dentro src o bin)
        String[] nomiRisorse = {"/pwdfile.txt", "pwdfile.txt", "/pwdfile", "pwdfile"};
        for (String risorsa : nomiRisorse) {
            InputStream is = DBConnection.class.getResourceAsStream(risorsa);
            if (is == null) {
                String nomeSenzaSlash = risorsa.startsWith("/") ? risorsa.substring(1) : risorsa;
                is = DBConnection.class.getClassLoader().getResourceAsStream(nomeSenzaSlash);
            }
            if (is != null) {
                return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            }
        }

        // 2. Prova percorsi candidati nel filesystem
        List<File> candidati = new ArrayList<>();
        String[] nomiFile = {"pwdfile.txt", "pwdfile"};

        // Working directory e percorsi relativi
        for (String nome : nomiFile) {
            candidati.add(new File(nome));
            candidati.add(new File("Progetto", nome));
            candidati.add(new File("..", nome));
            candidati.add(new File("../Progetto", nome));
        }

        // Risale la cartella della classe compilata (bin/ -> Progetto/)
        try {
            URL location = DBConnection.class.getProtectionDomain().getCodeSource().getLocation();
            if (location != null) {
                File dir = new File(location.toURI());
                if (!dir.isDirectory()) {
                    dir = dir.getParentFile();
                }
                while (dir != null) {
                    for (String nome : nomiFile) {
                        candidati.add(new File(dir, nome));
                        candidati.add(new File(new File(dir, "Progetto"), nome));
                    }
                    dir = dir.getParentFile();
                }
            }
        } catch (Exception ignored) {
        }

        for (File f : candidati) {
            if (f.exists() && f.isFile() && f.canRead()) {
                return new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
            }
        }

        throw new IOException("File pwdfile.txt non trovato né nel classpath né nel filesystem.");
    }

    private static class DriverShim implements Driver {
        private final Driver driver;

        public DriverShim(Driver driver) {
            this.driver = driver;
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return driver != null && driver.acceptsURL(url);
        }

        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return driver != null ? driver.connect(url, info) : null;
        }

        @Override
        public int getMajorVersion() {
            return driver != null ? driver.getMajorVersion() : 1;
        }

        @Override
        public int getMinorVersion() {
            return driver != null ? driver.getMinorVersion() : 0;
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return driver != null ? driver.getPropertyInfo(url, info) : new DriverPropertyInfo[0];
        }

        @Override
        public boolean jdbcCompliant() {
            return driver != null && driver.jdbcCompliant();
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return driver != null ? driver.getParentLogger() : null;
        }
    }
}
