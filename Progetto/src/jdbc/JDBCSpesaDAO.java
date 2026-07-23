package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SpesaDAO;
import model.Gruppo;
import model.Spesa;
import model.Utente;

public class JDBCSpesaDAO implements SpesaDAO {

private Connection conn;
private JDBCUtenteDAO utenteDAO=new JDBCUtenteDAO();
private JDBCGruppoDAO gruppoDAO=new JDBCGruppoDAO(utenteDAO);
private JDBCValutaDAO valutaDAO=new JDBCValutaDAO();
	
	public JDBCSpesaDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	@Override
	public void nuovaSpesa(Spesa s) {
		String sql="INSERT INTO SPESA(nomespesa,descrizione,data,importo,iscomune,idgruppo,emailutente,valuta) VALUES (?,?,?,?,?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, s.getNomeSpesa());
			ps.setString(2, s.getDescrizione());
			ps.setDate(3, Date.valueOf(s.getData()));
			ps.setDouble(4, s.getImporto());
			if(s.isComune()==true) {
				ps.setInt(5, 1);
			}
			else if(s.isComune()==false) {
				ps.setInt(5, 0);
			}
			ps.setInt(6, s.getGruppo().getId());
			ps.setString(7, s.getUtenteEffettuante().getEmailIstituzionale());
			ps.setString(8, s.getValuta().getNome());
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt(1); 
		                s.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento della spesa: "+e.getMessage());
		}
		
	}

	@Override
	public void aggiornaSpesa(Spesa s) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spesa cercaSpesaById(int id) {
		String sql="SELECT * FROM SPESA WHERE idspesa=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("iscomune")==1) {
					return new Spesa(rs.getInt("id"), rs.getString("nomespesa"), rs.getString("Descrizione"), 
							rs.getDate("data").toLocalDate(),rs.getDouble("importo"),true,
							valutaDAO.cercaValuta(rs.getString("valuta")),gruppoDAO.cercaGruppoById(rs.getInt("idgruppo")), 
							utenteDAO.cercaUtentePerEmail(rs.getString("emailutente"))
							);
				}
				else {
					return new Spesa(rs.getInt("id"), rs.getString("nomespesa"), rs.getString("Descrizione"), 
							rs.getDate("data").toLocalDate(),rs.getDouble("importo"),false,
							valutaDAO.cercaValuta(rs.getString("valuta")),gruppoDAO.cercaGruppoById(rs.getInt("idgruppo")), 
							utenteDAO.cercaUtentePerEmail(rs.getString("emailutente"))
							);
				}		
			}
			return null;
		}
		catch(SQLException e) {
			throw new RuntimeException("Nessuna spesa trovata");
		}
	}

	@Override
	public ArrayList<Spesa> cercaSpesaByGruppo(Gruppo g, Utente u) {
		String sql="SELECT * FROM Spesa WHERE IdGruppo=? AND (EmailUtente=? OR isComune=1)";
		ArrayList<Spesa> spese= new ArrayList<Spesa>();
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, g.getId());
			ps.setString(2, u.getEmailIstituzionale());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Spesa s;
				if(rs.getInt("iscomune")==1) {
					 s=new Spesa(rs.getInt("id"), rs.getString("nomespesa"), rs.getString("Descrizione"), 
							rs.getDate("data").toLocalDate(),rs.getDouble("importo"),true,
							valutaDAO.cercaValuta(rs.getString("valuta")),g, u);
				}
				else {
					 s=new Spesa(rs.getInt("id"), rs.getString("nomespesa"), rs.getString("Descrizione"), 
							rs.getDate("data").toLocalDate(),rs.getDouble("importo"),false,
							valutaDAO.cercaValuta(rs.getString("valuta")),g, u);
				}
				spese.add(s);
			}
			return spese;
		}
		catch(SQLException e) {
			throw new RuntimeException("Nessuna spesa trovata per il gruppo "+g.getNome());
		}
	}

	@Override
	public void eliminaSpesa(Spesa s) {
			String sql="DELETE * FROM SPESA WHERE idspesa=?";
			try(PreparedStatement ps=conn.prepareStatement(sql)){
				ps.setInt(1, s.getId());
				ps.executeQuery();
			}
			catch(SQLException e) {
				throw new RuntimeException("Nessuna spesa trovata");
			}


	}

}
