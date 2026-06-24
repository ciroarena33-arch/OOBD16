package jdbc;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UtenteDAO;
import model.Utente;

public class JDBCUtenteDAO implements UtenteDAO {
	private Connection conn;
	
	public JDBCUtenteDAO(Connection conn) {
		this.conn=conn;
	}

	@Override
	public void nuovoUtente(Utente u) {
		String sql="INSERT INTO Utente (EmailIstituzionale, password, nome, cognome) VALUES (?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getNome());
			ps.setString(4, u.getCognome());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore in inserimento del nuovo utente");
		}
	}

	@Override
	public Utente cercaUtentePerEmail(String email) {
		String sql="SELECT * FROM Utente WHERE EmailIstituzionale=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return new Utente(
						rs.getString("EmailIstituzionale"),
						rs.getString("Password"),
						rs.getString("Cognome"),
						rs.getString("Nome"),
						rs.getInt("Telefono"));
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore durante la ricerca dell'utente con mail "+email,e);
		}

	}

	@Override
	public void aggiornaUtente(String email, String password, String nome, String cognome, long telefono) {
		String sql="UPDATE on UTENTE SET password=?, nome=?, cognome=?, telefono=? WHERE email=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, password);
			ps.setString(2, nome);
			ps.setString(3, cognome);
			ps.setLong(4, telefono);
			ps.setString(5,  email);
			ps.executeQuery();
		}
		catch(SQLException e) {
			
		}
		
	}

	@Override
	public void eliminaUtente(String email) {
		String sql="DELETE FROM Utente WHERE EmailIstituzionale=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, email);
			ps.executeQuery();
		}
		catch(SQLException e){
			throw new RuntimeException("Errore durante la cancellazione dell'utente con email"+email,e);
		}
	}

	@Override
	public ArrayList<Utente> tuttiGliUtenti() {
		ArrayList<Utente> utenti=new ArrayList<>();
		String sql="SELECT * FROM Utente";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Utente u=new Utente(
						rs.getString("EmailIstituzionale"),
						rs.getString("Password"),
						rs.getString("Cognome"),
						rs.getString("Nome"),
						rs.getInt("Telefono"));
				utenti.add(u);
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore durante la selezione di tutti gli utenti", e);
		}
		return utenti;
	}

	
	
	
}
