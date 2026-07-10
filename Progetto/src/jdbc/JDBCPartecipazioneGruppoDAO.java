package jdbc;


import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.PartecipazioneGruppoDAO;

public class JDBCPartecipazioneGruppoDAO implements PartecipazioneGruppoDAO {

	private JDBCGruppoDAO gruppoDAO;
	private JDBCUtenteDAO utenteDAO;
	public Connection conn;
	
	public JDBCPartecipazioneGruppoDAO(JDBCGruppoDAO gruppoDAO, JDBCUtenteDAO utenteDAO) {
		this.conn = DBConnection.getDBConnection().getConnection();
		this.gruppoDAO=gruppoDAO;
		this.utenteDAO=utenteDAO;
	}
	@Override
	public void nuovaPartecipazione(PartecipazioneGruppo p) {
		
	}

	@Override
	public void aggiornaPartecipazione(PartecipazioneGruppo p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<PartecipazioneGruppo> cercaPartecipazioniByUtenteId(Utente user) {
		ArrayList<PartecipazioneGruppo> lista=new ArrayList<>();
		String sql="SELECT * FROM PartecipazioneGruppo WHERE emailUtente=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, user.getEmailIstituzionale());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				LocalDate data = rs.getObject("DataInvito", LocalDate.class);
				PartecipazioneGruppo p=new PartecipazioneGruppo(
						rs.getInt("idpartecipazione"),
						data,
						rs.getBoolean("statoInvito"),
						user,
						gruppoDAO.cercaGruppoById(rs.getInt("idGruppo"))
						);
				lista.add(p);
				user.addGruppo(p);
			}
			return lista;
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca delle tue partecipazioni ai gruppi");
		}
		
	}

	@Override
	public ArrayList<PartecipazioneGruppo> cercaPartecipazioniByGruppoId(Gruppo g) {
		ArrayList<PartecipazioneGruppo> lista=new ArrayList<>();
		String sql="SELECT * FROM PartecipazioneGruppo WHERE idgruppo=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, g.getId());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				LocalDate data = rs.getObject("DataInvito", LocalDate.class);
				PartecipazioneGruppo p=new PartecipazioneGruppo(
						rs.getInt("idpartecipazione"),
						data,
						rs.getBoolean("statoInvito"),
						utenteDAO.cercaUtentePerEmail(rs.getString("emailUtente")),
						g
						);
				lista.add(p);
				g.addComponente(p);
			}
			return lista;
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca delle tue partecipazioni ai gruppi");
		}
	}

	@Override
	public void eliminaPartecipazione(PartecipazioneGruppo p) {
		String sql="DELETE FROM PartecipazioneGruppo WHERE idPartecipazione=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, p.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella cancellazione del gruppo "+p.getGruppo().getNome());
		}
	}

}
