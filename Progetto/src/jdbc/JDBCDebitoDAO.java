package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DebitoDAO;
import model.Debito;
import model.Spesa;
import model.Utente;

public class JDBCDebitoDAO implements DebitoDAO {

	private Connection conn;
	private JDBCSpesaDAO spesaDAO=new JDBCSpesaDAO();
	private JDBCUtenteDAO utenteDAO=new JDBCUtenteDAO();
		
	public JDBCDebitoDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	
	@Override
	public void nuovoDebito(Debito d) {
		String sql="Insert into OOBD16.DEBITO (importo,saldato,idspesa,emailutente) values (?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setDouble(1, d.getImporto());
			if(d.isDebitoSaldato()) {
				ps.setInt(2, 1);
			}
			else {
				ps.setInt(2, 0);
			}
			ps.setInt(3, d.getSpesa().getId());
			ps.setString(4, d.getDebitore().getEmailIstituzionale());
			int righeInserite = ps.executeUpdate();
			if (righeInserite>0) {
				try(ResultSet rs=ps.getGeneratedKeys()){
					int idGenerato=rs.getInt(1);
					d.setId(idGenerato);
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del debito, "+e.getMessage());
		}
	}

	@Override
	public void aggiornaDebito(Debito d) {
		
	}

	@Override
	public Debito cercaDebitoById(int id) {
		String sql="SELECT * FROM DEBITO WHERE iddebito=?";
		Debito d=null;
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("isComune")==1) {
					d=new Debito(id, spesaDAO.cercaSpesaById(rs.getInt("idSpesa")), 
						utenteDAO.cercaUtentePerEmail(rs.getString("emailutente")), rs.getDouble("importo"), true);
				}
				else {
					d=new Debito(id, spesaDAO.cercaSpesaById(rs.getInt("idSpesa")), 
						utenteDAO.cercaUtentePerEmail(rs.getString("emailutente")), rs.getDouble("importo"), false);
				}				
			}			
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del debito, "+e.getMessage());
		}
		return d;
	}

	@Override
	public ArrayList<Debito> cercaDebitoBySpesa(Spesa s) {
		String sql="SELECT * FROM DEBITO WHERE idSpesa=?";
		ArrayList<Debito> debiti=new ArrayList<Debito>();
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, s.getId());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Debito d;
				if(rs.getInt("isComune")==1) {
					d=new Debito(rs.getInt("idDebito"), s, utenteDAO.cercaUtentePerEmail(rs.getString("emailutente")),
						rs.getDouble("importo"), true);
				}
				else {
					d=new Debito(rs.getInt("idDebito"), s,utenteDAO.cercaUtentePerEmail(rs.getString("emailutente")),
						rs.getDouble("importo"), false);
				}
				debiti.add(d);
			}			
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca dei debiti, "+e.getMessage());
		}
		return debiti;
	}

	@Override
	public ArrayList<Debito> cercaDebitoByUtente(Utente u) {
		String sql="SELECT * FROM DEBITO WHERE emaillutente=?";
		ArrayList<Debito> debiti=new ArrayList<Debito>();
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Debito d;
				if(rs.getInt("isComune")==1) {
					d=new Debito(rs.getInt("idDebito"), spesaDAO.cercaSpesaById(rs.getInt("idSpesa")), 
							u, rs.getDouble("importo"), true);
				}
				else {
					d=new Debito(rs.getInt("idDebito"), spesaDAO.cercaSpesaById(rs.getInt("idSpesa")), 
							u, rs.getDouble("importo"), false);
				}
				debiti.add(d);
			}			
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca dei debiti, "+e.getMessage());
		}
		return debiti;
	}

	@Override
	public Debito cercaDebitoUtenteSpesa(Utente u, Spesa s) {
		String sql="SELECT * FROM DEBITO WHERE emailUtente=? AND idspesa=?";
		Debito d=null;
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ps.setInt(2, s.getId());
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("isComune")==1) {
					d=new Debito(rs.getInt("iddebito"), s, u, rs.getDouble("importo"), true);
				}
				else {
					d=new Debito(rs.getInt("iddebito"), s, u, rs.getDouble("importo"), false);
				}				
			}			
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca del debito, "+e.getMessage());
		}
		return d;
	}

	@Override
	public void eliminaDebito(Debito d) {
		String sql="DELETE * FROM DEBITO WHERE iddebito=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, d.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca del debito, "+e.getMessage());
		}
	}

}
