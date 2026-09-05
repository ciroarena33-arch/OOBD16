package jdbc;


import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.PartecipazioneGruppoDAO;

public class JDBCPartecipazioneGruppoDAO implements PartecipazioneGruppoDAO {

	public Connection conn;
	private JDBCGruppoDAO gruppoDAO;
	private JDBCUtenteDAO utenteDAO;
	
	private JDBCPartecipazioneGruppoDAO() {
		this.conn = DBConnection.getDBConnection().getConnection();
		this.gruppoDAO=JDBCGruppoDAO.getSelf();
		this.utenteDAO=JDBCUtenteDAO.getSelf();
	}
	
	private static JDBCPartecipazioneGruppoDAO self=null;
	public static synchronized JDBCPartecipazioneGruppoDAO getSelf() {
		if(self==null) {
			self=new JDBCPartecipazioneGruppoDAO();
		}
		return self;
	}
	
	@Override
	public void nuovaPartecipazione(PartecipazioneGruppo p) {
		String sql="INSERT INTO PartecipazioneGruppo (datainvito,statoinvito,emailutente,idgruppo) VALUES (?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setDate(1,  Date.valueOf(p.getData()));
			ps.setInt(2, 0);
			ps.setString(3,  p.getUtente().getEmailIstituzionale());
			ps.setInt(4,  p.getGruppo().getId());
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
			        if (!rs.next()) throw new SQLException("Chiave generata non disponibile per la partecipazione");
			        p.setId(rs.getInt(1));
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento della partecipazione");
		}
	}

	@Override
	public void aggiornaPartecipazione(PartecipazioneGruppo p) {
		String sql="UPDATE PartecipazioneGruppo SET datainvito=? ,statoinvito=? WHERE idpartecipazione=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setDate(1,  Date.valueOf(p.getData()));
			ps.setInt(2, 1);
			ps.setInt(3,  p.getId());
			ps.execute();		
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'aggiornamento della partecipazione");
		}		
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
				int i=rs.getInt("idpartecipazione");
				Boolean statoInvito=rs.getBoolean("statoInvito");
				PartecipazioneGruppo p=new PartecipazioneGruppo(
						i,
						data,
						statoInvito,
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
	
	
	@Override
	public PartecipazioneGruppo getPartecipazione(Utente u, Gruppo g) {
		
		String sql="SELECT * FROM PartecipazioneGruppo WHERE emailUtente=? AND idgruppo=?";
		PartecipazioneGruppo p=null;
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ps.setInt(2, g.getId());
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				LocalDate data = rs.getObject("DataInvito", LocalDate.class);
				p=new PartecipazioneGruppo(
						rs.getInt("idpartecipazione"),
						data,
						rs.getBoolean("statoInvito"),
						u,
						g
						);
				u.addGruppo(p);
			}
			
			return p;
		}
		catch(SQLException e) {
			throw new RuntimeException("L'Utente con mail"+u.getEmailIstituzionale()+" non appartiene al gruppo");
		}
		
	}

}
