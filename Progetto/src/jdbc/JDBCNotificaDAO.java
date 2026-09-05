package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.NotificaDAO;
import model.Notifica;
import model.Utente;

public class JDBCNotificaDAO implements NotificaDAO {

    private Connection conn;
    private JDBCDebitoDAO debitoDAO;

    private JDBCNotificaDAO() {
        this.conn = DBConnection.getDBConnection().getConnection();
        this.debitoDAO = JDBCDebitoDAO.getSelf();
    }

    private static JDBCNotificaDAO self = null;

    public static synchronized JDBCNotificaDAO getSelf() {
        if (self == null) {
            self = new JDBCNotificaDAO();
        }
        return self;
    }

    @Override
    public void nuovaNotifica(Notifica n) {
        String sql = "INSERT INTO NOTIFICA (DATA1,DESCRIZIONE1,IDDEBITO) VALUES (?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(n.getData1()));
            ps.setString(2, n.getDescrizione1());
            ps.setInt(3, n.getDebito().getId());
            int righeInserite = ps.executeUpdate();
            if (righeInserite > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new SQLException("Chiave generata non disponibile per la notifica");
                    n.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'inserimento della notifica, " + e.getMessage());
        }
    }

    @Override
    public void rispostaNotifica(Notifica n) {
        String sql = "UPDATE NOTIFICA SET datarisposta=?, descrizionerisposta=? WHERE idnotifica=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(n.getDataRisposta()));
            ps.setString(2, n.getDescrizioneRisposta());
            ps.setInt(3, n.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento della notifica, " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Notifica> cercaNotificheInviateByUtente(Utente u) {
        ArrayList<Notifica> lista = new ArrayList<>();
        String sql = "SELECT N.* FROM Notifica N "
                   + "JOIN Debito D ON N.iddebito = D.iddebito "
                   + "JOIN Spesa S ON D.idspesa = S.idspesa "
                   + "WHERE S.emailUtente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmailIstituzionale());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LocalDate data1 = rs.getObject("Data1", LocalDate.class);
                LocalDate datarisposta = rs.getObject("DataRisposta", LocalDate.class);
                Notifica p = new Notifica(
                        rs.getInt("idnotifica"),
                        debitoDAO.cercaDebitoById(rs.getInt("iddebito")),
                        data1,
                        rs.getString("descrizione1"),
                        datarisposta,
                        rs.getString("descrizioneRisposta")
                );
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle notifiche inviate, " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Notifica> cercaNotificheRicevuteByUtente(Utente u) {
        ArrayList<Notifica> lista = new ArrayList<>();
        String sql = "SELECT N.* FROM Notifica N "
                   + "JOIN DEBITO D ON N.idDebito = D.idDebito "
                   + "WHERE D.emailUtente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmailIstituzionale());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LocalDate data1 = rs.getObject("Data1", LocalDate.class);
                    LocalDate datarisposta = rs.getObject("DataRisposta", LocalDate.class);

                    Notifica p = new Notifica(
                            rs.getInt("idnotifica"),
                            debitoDAO.cercaDebitoById(rs.getInt("iddebito")),
                            data1,
                            rs.getString("descrizione1"),
                            datarisposta,
                            rs.getString("descrizioneRisposta")
                    );
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle notifiche ricevute, " + e.getMessage(), e);
        }
        return lista;
    }

    @Override
    public void eliminaNotifica(Notifica n) {
        String sql = "DELETE FROM Notifica WHERE idNotifica=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, n.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella cancellazione della notifica");
        }
    }

    @Override
    public Notifica cercaNotificaById(int id) {
        String sql = "SELECT * FROM Notifica WHERE idNotifica=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LocalDate data1 = rs.getObject("Data1", LocalDate.class);
                LocalDate datarisposta = rs.getObject("DataRisposta", LocalDate.class);
                return new Notifica(
                        rs.getInt("idnotifica"),
                        debitoDAO.cercaDebitoById(rs.getInt("iddebito")),
                        data1,
                        rs.getString("descrizione1"),
                        datarisposta,
                        rs.getString("descrizioneRisposta")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della notifica per id");
        }
    }
}
