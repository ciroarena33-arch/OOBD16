package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ScadenzaDAO;
import model.Coinquilini;
import model.Scadenza;

public class JDBCScadenzaDAO implements ScadenzaDAO {

    private Connection conn;

    private JDBCScadenzaDAO() {
        this.conn = DBConnection.getDBConnection().getConnection();
    }

    private static JDBCScadenzaDAO self = null;

    public static synchronized JDBCScadenzaDAO getSelf() {
        if (self == null) {
            self = new JDBCScadenzaDAO();
        }
        return self;
    }

    @Override
    public void nuovaScadenza(Scadenza s) {
        String sql = "INSERT INTO Scadenza(nomescadenza,data,importo,idgruppo) VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNome());
            ps.setDate(2, Date.valueOf(s.getDataScadenza()));
            ps.setDouble(3, s.getImporto());
            ps.setInt(4, s.getGruppo().getId());
            int righeInserite = ps.executeUpdate();
            if (righeInserite > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerato = rs.getInt(1);
                        s.setId(idGenerato);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella creazione della scadenza, " + e.getMessage());
        }
    }

    @Override
    public void aggiornaScadenza(Scadenza s) {
        String sql = "UPDATE Scadenza SET nomescadenza=?, data=?, importo=? WHERE idscadenza=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNome());
            ps.setDate(2, Date.valueOf(s.getDataScadenza()));
            ps.setDouble(3, s.getImporto());
            ps.setInt(4, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'aggiornamento della scadenza, " + e.getMessage());
        }
    }

    @Override
    public Scadenza cercaScadenzaById(int id) {
        String sql = "SELECT * FROM Scadenza WHERE idscadenza=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Scadenza(
                        rs.getInt("idscadenza"),
                        rs.getString("nomescadenza"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        null
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della scadenza, " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Scadenza> cercaScadenzePerGruppo(Coinquilini g) {
        String sql = "SELECT * FROM SCADENZA WHERE idgruppo=?";
        ArrayList<Scadenza> scadenze = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Scadenza s = new Scadenza(
                        rs.getInt("idscadenza"),
                        rs.getString("nomescadenza"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        g
                );
                scadenze.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle scadenze, " + e.getMessage());
        }
        return scadenze;
    }

    @Override
    public void eliminaScadenza(Scadenza s) {
        String sql = "DELETE FROM Scadenza WHERE idscadenza=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella cancellazione della scadenza, " + e.getMessage());
        }
    }
}
