package jdbc;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.IndirizzoDAO;
import model.Indirizzo;

public class JDBCIndirizzoDAO implements IndirizzoDAO{

	private Connection conn;
	
	private JDBCIndirizzoDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	
	private static JDBCIndirizzoDAO self=null;
	public static synchronized JDBCIndirizzoDAO getSelf() {
		if(self==null) {
			self=new JDBCIndirizzoDAO();
		}
		return self;
	}
	@Override
	public void nuovoIndirizzo(Indirizzo i) {
		String sql="INSERT INTO Indirizzo (provincia, citta, via, numcivico) VALUES (?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, i.getProvincia());
			ps.setString(2, i.getCitta());
			ps.setString(3, i.getVia());
			ps.setInt(4, i.getNumCivico());
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt("idIndirizzo"); 
		                i.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			
		}
	}

	@Override
	public Indirizzo cercaIndirizzoById(int id) {
		String sql="SELECT * FROM Indirizzo WHERE idindirizzo=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return new Indirizzo(
						rs.getInt("idindirizzo"),
						rs.getString("provincia"),
						rs.getString("citta"),
						rs.getString("via"),
						rs.getInt("numcivico")
						);
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca dell'indirizzo, "+e.getMessage());
		}
	}

	@Override
	public void aggiornaIndirizzo(Indirizzo i) {
		String sql="UPDATE Indirizzo SET provincia=?, citta=?, via=?, numcivico=? WHERE idindirizzo=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, i.getProvincia());
			ps.setString(2, i.getCitta());
			ps.setString(3, i.getVia());
			ps.setInt(4, i.getNumCivico());
			ps.setInt(5, i.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore: "+ e.getMessage());
		}
	}

	@Override
	public void eliminaIndirizzo(int id) {
		String sql="DELETE FROM Indirizzo WHERE idindirizzo=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca dell'indirizzo, "+e.getMessage());
		}
	}

}
