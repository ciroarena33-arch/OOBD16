package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DebitoDAO;
import model.Debito;
import model.Gruppo;
import model.Spesa;
import model.Utente;

public class JDBCDebitoDAO implements DebitoDAO {

    private Connection conn;
    private JDBCSpesaDAO spesaDAO;
    private JDBCUtenteDAO utenteDAO;

    private JDBCDebitoDAO() {
        this.conn = DBConnection.getDBConnection().getConnection();
        this.spesaDAO = JDBCSpesaDAO.getSelf();
        this.utenteDAO = JDBCUtenteDAO.getSelf();
    }

    private static JDBCDebitoDAO self = null;

    public static synchronized JDBCDebitoDAO getSelf() {
        if (self == null) {
            self = new JDBCDebitoDAO();
        }
        return self;
    }

    @Override
    public void nuovoDebito(Debito d) {
        String sql = "INSERT INTO DEBITO (importo,saldato,idspesa,emailutente) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, d.getImporto());
            ps.setInt(2, d.isDebitoSaldato() ? 1 : 0);
            ps.setInt(3, d.getSpesa().getId());
            ps.setString(4, d.getDebitore().getEmailIstituzionale());
            int righeInserite = ps.executeUpdate();
            if (righeInserite > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerato = rs.getInt(1);
                        d.setId(idGenerato);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'inserimento del debito, " + e.getMessage());
        }
    }

    @Override
    public void aggiornaDebito(Debito d) {
        String sql = "UPDATE DEBITO SET importo=?, saldato=? WHERE iddebito=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, d.getImporto());
            ps.setInt(2, d.isDebitoSaldato() ? 1 : 0);
            ps.setInt(3, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento del debito, " + e.getMessage());
        }
    }

    @Override
    public Debito cercaDebitoById(int id) {
        String sql = "SELECT * FROM DEBITO WHERE iddebito=?";
        Debito d = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean saldato = rs.getInt("saldato") == 1;
                d = new Debito(
                        id,
                        spesaDAO.cercaSpesaById(rs.getInt("idSpesa")),
                        utenteDAO.cercaUtentePerEmail(rs.getString("emailutente")),
                        rs.getDouble("importo"),
                        saldato
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca del debito, " + e.getMessage());
        }
        return d;
    }

    @Override
    public ArrayList<Debito> cercaDebitoByGruppoUtente(Gruppo g, Utente u) {
        String sql = "SELECT D.IDDEBITO, D.IMPORTO, D.SALDATO, D.IDSPESA " +
                     "FROM DEBITO D " +
                     "JOIN SPESA S ON D.IDSPESA = S.IDSPESA " +
                     "WHERE S.IDGRUPPO = ? AND D.EMAILUTENTE = ?";

        ArrayList<Debito> debiti = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ps.setString(2, u.getEmailIstituzionale());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idDebito = rs.getInt("IDDEBITO");
                    double importo = rs.getDouble("IMPORTO");
                    boolean saldato = rs.getInt("SALDATO") == 1;
                    int idSpesa = rs.getInt("IDSPESA");
                    Spesa spesa = spesaDAO.cercaSpesaById(idSpesa);

                    Debito d = new Debito(idDebito, spesa, u, importo, saldato);
                    debiti.add(d);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca dei debiti: " + e.getMessage(), e);
        }
        return debiti;
    }

    @Override
    public ArrayList<Debito> cercaDebitoByUtente(Utente u) {
        String sql = "SELECT * FROM DEBITO WHERE emailutente=?";
        ArrayList<Debito> debiti = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmailIstituzionale());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boolean saldato = rs.getInt("saldato") == 1;
                Debito d = new Debito(
                        rs.getInt("idDebito"),
                        spesaDAO.cercaSpesaById(rs.getInt("idSpesa")),
                        u,
                        rs.getDouble("importo"),
                        saldato
                );
                debiti.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca dei debiti, " + e.getMessage());
        }
        return debiti;
    }

    @Override
    public Debito cercaDebitoUtenteSpesa(Utente u, Spesa s) {
        String sql = "SELECT * FROM DEBITO WHERE emailUtente=? AND idspesa=?";
        Debito d = null;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getEmailIstituzionale());
            ps.setInt(2, s.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean saldato = rs.getInt("saldato") == 1;
                d = new Debito(rs.getInt("iddebito"), s, u, rs.getDouble("importo"), saldato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca del debito, " + e.getMessage());
        }
        return d;
    }

    @Override
    public void eliminaDebito(Debito d) {
        String sql = "DELETE FROM DEBITO WHERE iddebito=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella cancellazione del debito, " + e.getMessage());
        }
    }
}
