package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.NotificaDAO;
import model.Notifica;
import model.Utente;

public class JDBCNotificaDAO implements NotificaDAO {

	private Connection conn;
	private JDBCDebitoDAO debitoDAO=new JDBCDebitoDAO();
	
	public JDBCNotificaDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	
	@Override
	public void nuovaNotifica(Notifica n) {
		String sql="Insert into NOTIFICA (DATA1,DESCRIZIONE1,IDDEBITO) values (?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setDate(1,Date.valueOf(n.getData1()));
			ps.setString(2, n.getDescrizione1());
			ps.setInt(3, n.getDebito().getId());
			int righeInserite=ps.executeUpdate();
			if(righeInserite>0) {
				try(ResultSet rs=ps.getGeneratedKeys()){
					int idGenerato=rs.getInt(1);
					n.setId(idGenerato);
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento della notifica, "+e.getMessage());
		}
	}

	@Override
	public void rispostaNotifica(Notifica n) {
		String sql="UPDATE NOTIFICA WHERE idnotifica=? SET datarisposta=? , descrizionerisposta=";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setDate(2,Date.valueOf(n.getDataRisposta()));
			ps.setString(3, n.getDescrizioneRisposta());
			ps.setInt(1, n.getId());
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento della notifica, "+e.getMessage());
		}
	}

	@Override
	public Notifica cercaNotificaById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Notifica> cercaNotificheInviateByUtente(Utente u) {
		ArrayList<Notifica> lista=new ArrayList<>();
		String sql="SELECT * FROM Notifica AS N"
				+ "NATURAL JOIN DEBITO AS D,"
				+ "JOIN SPESA AS S ON D.idspesa=S.idspesa"
				+ "WHERE S.emailUtente=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				LocalDate data1 = rs.getObject("Data1", LocalDate.class);
				LocalDate datarisposta = rs.getObject("DataRisposta", LocalDate.class);
				Notifica p=new Notifica(
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
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerche delle notifiche inviate dall'utente u");
		}
		return lista;

	}

	@Override
	public ArrayList<Notifica> cercaNotificheRicevuteByUtente(Utente u) {
		ArrayList<Notifica> lista=new ArrayList<>();
		String sql="SELECT * FROM Notifica AS N"
				+ "NATURAL JOIN DEBITO AS D,"
				+ "WHERE D.emailUtente=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, u.getEmailIstituzionale());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				LocalDate data1 = rs.getObject("Data1", LocalDate.class);
				LocalDate datarisposta = rs.getObject("DataRisposta", LocalDate.class);
				Notifica p=new Notifica(
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
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerche delle notifiche inviate dall'utente u");
		}
		return lista;
	}

	@Override
	public void eliminaNotifica(Notifica n) {
		String sql="DELETE FROM Notifica WHERE idNotifica=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, n.getId());
			ps.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella cancellazione della notifica");
		}
	}

}
