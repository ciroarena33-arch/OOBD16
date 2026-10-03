package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.SpesaDAO;
import model.Gruppo;
import model.Spesa;
import model.Utente;

public class JDBCSpesaDAO implements SpesaDAO {

    private Connection conn;
    private JDBCUtenteDAO utenteDAO;
    private JDBCGruppoDAO gruppoDAO;
    private JDBCValutaDAO valutaDAO;

    public JDBCSpesaDAO() {
        this.conn = DBConnection.getDBConnection().getConnection();
        this.utenteDAO =new JDBCUtenteDAO();
        this.gruppoDAO =new JDBCGruppoDAO();
        this.valutaDAO =new JDBCValutaDAO();
    }

    @Override
    public void nuovaSpesa(Spesa s) {
        String sql = "INSERT INTO SPESA(nomespesa,descrizione,data,importo,iscomune,idgruppo,emailutente,valuta) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getNomeSpesa());
            ps.setString(2, s.getDescrizione());
            ps.setDate(3, Date.valueOf(s.getData()));
            ps.setDouble(4, s.getImporto());
            ps.setInt(5, s.isComune() ? 1 : 0);
            ps.setInt(6, s.getGruppo().getId());
            ps.setString(7, s.getUtenteEffettuante().getEmailIstituzionale());
            ps.setString(8, s.getValuta().getNome());
            int righeInserite = ps.executeUpdate();
            if (righeInserite > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new SQLException("Chiave generata non disponibile per la spesa");
                    s.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore nell'inserimento della spesa: " + e.getMessage());
        }
    }

    @Override
    public void aggiornaSpesa(Spesa s) {
    }

    @Override
    public Spesa cercaSpesaById(int id) {
        String sql = "SELECT * FROM SPESA WHERE idspesa=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean isComune = rs.getInt("iscomune") == 1;
                return new Spesa(
                        rs.getInt("idspesa"),
                        rs.getString("nomespesa"),
                        rs.getString("Descrizione"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        isComune,
                        valutaDAO.cercaValuta(rs.getString("valuta")),
                        gruppoDAO.cercaGruppoById(rs.getInt("idgruppo")),
                        utenteDAO.cercaUtentePerEmail(rs.getString("emailutente"))
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca della spesa, " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Spesa> cercaSpeseComuni(Gruppo g) {
        String sql = "SELECT * FROM Spesa WHERE IdGruppo=? AND isComune=?";
        ArrayList<Spesa> spese = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spesa s = new Spesa(
                        rs.getInt("idspesa"),
                        rs.getString("nomespesa"),
                        rs.getString("Descrizione"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        true,
                        valutaDAO.cercaValuta(rs.getString("valuta")),
                        g,
                        utenteDAO.cercaUtentePerEmail(rs.getString("emailUtente"))
                );
                spese.add(s);
            }
            return spese;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle spese comuni del gruppo, " + e.getMessage());
        }
    }

    public ArrayList<Spesa> cercaTutteSpeseByGruppo(Gruppo g) {
        String sql = "SELECT * FROM Spesa WHERE IdGruppo=?";
        ArrayList<Spesa> spese = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                spese.add(new Spesa(
                        rs.getInt("idspesa"),
                        rs.getString("nomespesa"),
                        rs.getString("Descrizione"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        rs.getInt("iscomune") == 1,
                        valutaDAO.cercaValuta(rs.getString("valuta")),
                        g,
                        utenteDAO.cercaUtentePerEmail(rs.getString("emailUtente"))
                ));
            }
            return spese;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle spese del gruppo", e);
        }
    }

    @Override
    public ArrayList<Spesa> cercaSpesePersonali(Gruppo g, Utente u) {
        String sql = "SELECT * FROM Spesa WHERE IdGruppo=? AND EmailUtente=? AND isComune=?";
        ArrayList<Spesa> spese = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ps.setString(2, u.getEmailIstituzionale());
            ps.setInt(3, 0);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Spesa s = new Spesa(
                        rs.getInt("idspesa"),
                        rs.getString("nomespesa"),
                        rs.getString("Descrizione"),
                        rs.getDate("data").toLocalDate(),
                        rs.getDouble("importo"),
                        false,
                        valutaDAO.cercaValuta(rs.getString("valuta")),
                        g,
                        u
                );
                spese.add(s);
            }
            return spese;
        } catch (SQLException e) {
            throw new RuntimeException("Errore nella ricerca delle spese personali dell'utente");
        }
    }

    @Override
    public void eliminaSpesa(Spesa s) {
        String sql = "DELETE FROM SPESA WHERE idspesa=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Nessuna spesa trovata per la cancellazione");
        }
    }

    public ArrayList<Spesa> cercaSpesaByGruppo(Gruppo gruppoSelezionato, Utente utenteLoggato) {
        ArrayList<Spesa> lista = cercaSpesePersonali(gruppoSelezionato, utenteLoggato);
        ArrayList<Spesa> listaComuni = cercaSpeseComuni(gruppoSelezionato);
        lista.addAll(listaComuni);
        return lista;
    }
}
