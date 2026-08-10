package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ValutaDAO;
import model.Valuta;

public class JDBCValutaDAO implements ValutaDAO {

	private Connection conn;
	
	private JDBCValutaDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	
	private static JDBCValutaDAO self=null;
	public static synchronized JDBCValutaDAO getSelf() {
		if(self==null) {
			self=new JDBCValutaDAO();
		}
		return self;
	}
	
	@Override
	public Valuta cercaValuta(String nome) {
		String sql="SELECT * FROM VALUTA WHERE nome=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, nome);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return new Valuta(rs.getString("nome"),rs.getDouble("conversioneEuro"));
			}
			return null;
		}
		catch(SQLException e) {
			throw new RuntimeException("Nessuna valuta trovata con nome "+nome);
		}
	}

	@Override
	public ArrayList<Valuta> tutteLeValute() {
		String sql="SELECT * FROM VALUTA";
		ArrayList<Valuta> valute=new ArrayList<Valuta>();
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Valuta v=new Valuta(rs.getString("nome"),rs.getDouble("conversioneEuro"));
				valute.add(v);
			}
			return valute;
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca delle valute");
		}
	}

}
