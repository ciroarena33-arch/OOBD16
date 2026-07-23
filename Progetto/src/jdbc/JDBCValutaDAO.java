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
	
	public JDBCValutaDAO() {
		this.conn=DBConnection.getDBConnection().getConnection();
	}
	
	@Override
	public Valuta cercaValuta(String nome) {
		nome=nome.toLowerCase();
		String sql="SELECT * FROM VALUTA WHERE nome=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, nome);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Valuta v=new Valuta(rs.getString("nome"),rs.getDouble("conversioneEuro"));
				return v;
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
