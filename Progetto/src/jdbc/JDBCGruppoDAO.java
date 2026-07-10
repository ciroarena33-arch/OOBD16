package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.GruppoDAO;
import model.Coinquilini;
import model.Gruppo;
import model.Indirizzo;
import model.Studio;
import model.Utente;
import model.Viaggio;
public class JDBCGruppoDAO implements GruppoDAO{

	private Connection conn;
	private JDBCUtenteDAO utenteDAO;
	private JDBCIndirizzoDAO indirizzoDAO;
	
	public JDBCGruppoDAO(JDBCUtenteDAO utenteDAO) {
		this.conn=DBConnection.getDBConnection().getConnection();
		this.utenteDAO=utenteDAO;
	}
	@Override
	public void inserisciGruppo(Gruppo g) {
		if (g == null) throw new RuntimeException("Gruppo nullo");

	    switch (g) {
	        case Studio gs -> insertGruppoStudio(gs);
	        case Coinquilini gc -> insertGruppoCoinquilini(gc);
	        case Viaggio gv -> insertGruppoViaggio(gv);
	        default -> insertGruppoGenerico(g);
	    };
	}

	private void insertGruppoGenerico(Gruppo g) {
		String sql="INSERT INTO Gruppo (Nome,Tipo, emailProprietario) VALUES (?,?, ?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1,  g.getNome());
			ps.setString(2, "Generico");
			ps.setString(3, g.getProprietario().getEmailIstituzionale());
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt(1); 
		                g.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del gruppo generico, "+e.getMessage());
		}
	}

	private void insertGruppoViaggio(Viaggio gv) {
		String sql="INSERT INTO Gruppo (Nome,Tipo,DataInizio,DataFine,Destinazione,emailProprietario) VALUES (?,?,?,?,?, ?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1,  gv.getNome());
			ps.setString(2, "Viaggio");
			ps.setDate(3,  java.sql.Date.valueOf(gv.getDataInizio()));
			ps.setDate(4,  java.sql.Date.valueOf(gv.getDataFine()));
			ps.setString(5,  gv.getDestinazione());
			ps.setString(6, gv.getProprietario().getEmailIstituzionale());
			
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt(1); 
		                gv.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del gruppo viaggio,  "+e.getMessage());
		}
	}

	private void insertGruppoCoinquilini(Coinquilini gc) {
		String sql="INSERT INTO Gruppo (Nome,Tipo,Indirizzo,emailProprietario) VALUES (?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1,  gc.getNome());
			ps.setString(2, "Coinquilini");
			ps.setInt(3, gc.getIndirizzo().getId());
			ps.setString(4, gc.getProprietario().getEmailIstituzionale());
			
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt(1); 
		                gc.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del gruppo coinquilini,  "+e.getMessage());
		}
	}

	private void insertGruppoStudio(Studio gs) {
		String sql="INSERT INTO Gruppo (Nome,Tipo,NomeEsame,DataAppello,EmailProprietario) VALUES (?,?,?,?,?)";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1,  gs.getNome());
			ps.setString(2, "Viaggio");
			ps.setString(3,  gs.getNomeEsame());
			ps.setDate(4,  java.sql.Date.valueOf(gs.getDataEsame()));
			ps.setString(5, gs.getProprietario().getEmailIstituzionale());
			
			int righeInserite = ps.executeUpdate();
		    if (righeInserite > 0) {
		        try (ResultSet rs = ps.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGenerato = rs.getInt(1); 
		                gs.setId(idGenerato);      
		            }
		        }
		    }
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del gruppo viaggio,  "+e.getMessage());
		}
	}

	@Override
	public Gruppo cercaGruppoById(int id) {
		String sql="SELECT * FROM gruppo WHERE idGruppo = ?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			Gruppo gruppo = null;
			if (rs.next()) {
	            String nome = rs.getString("nome");
	            String emailProprietario = rs.getString("emailproprietario"); 
	            Utente proprietario = utenteDAO.cercaUtentePerEmail(emailProprietario);
	            String tipoGruppo = rs.getString("tipo"); 
	            
	            
	            switch (tipoGruppo.toUpperCase()) {
	                
	                case "VIAGGIO":
	                    LocalDate dataInizio = rs.getDate("datainizio").toLocalDate();
	                    LocalDate dataFine = rs.getDate("datafine").toLocalDate();
	                    String destinazione = rs.getString("destinazione");
	                    
	                    gruppo = new Viaggio(id, nome, proprietario, dataInizio, dataFine, destinazione);
	                    break;

	                case "COINQUILINI":
	                    int idIndirizzo = rs.getInt("idindirizzo");
	                    Indirizzo indirizzo = indirizzoDAO.cercaIndirizzoById(idIndirizzo);
	                    
	                    gruppo = new Coinquilini(id, nome, proprietario, indirizzo);
	                    break;

	                case "STUDIO":
	                    LocalDate dataAppello = rs.getDate("dataappello").toLocalDate();
	                    String nomeEsame = rs.getString("nomeesame");
	                    
	                    gruppo = new Studio(id, nome, proprietario, nomeEsame, dataAppello);
	                    break;

	                default:
	                    gruppo = new Gruppo(id, nome, proprietario);
	                    break;
	            }
	            
	        }
			return gruppo;
			
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nell'inserimento del gruppo viaggio,  "+e.getMessage());
		}
	
		
	}

	@Override
	public void aggiornaGruppo(Gruppo gruppo) {
		    if (gruppo == null) {
		        throw new IllegalArgumentException("Il gruppo da aggiornare cannot be null.");
		    }
		    	String sql;		    
		    if (gruppo instanceof Viaggio) {
		        sql = "UPDATE gruppi SET nome = ?, email_proprietario = ?, data_inizio = ?, data_fine = ?, destinazione = ? WHERE idGruppo = ?";
		    } else if (gruppo instanceof Coinquilini) {
		        sql = "UPDATE gruppi SET nome = ?, email_proprietario = ?, id_indirizzo = ? WHERE idGruppo = ?";
		    } else if (gruppo instanceof Studio) {
		        sql = "UPDATE gruppi SET nome = ?, email_proprietario = ?, data_appello = ?, nome_esame = ? WHERE idGruppo = ?";
		    } else {
		        sql = "UPDATE gruppi SET nome = ?, email_proprietario = ? WHERE idGruppo = ?";
		    }

		    try (PreparedStatement ps = conn.prepareStatement(sql)) {
		        
		        ps.setString(1, gruppo.getNome());
		        ps.setString(2, gruppo.getProprietario().getEmailIstituzionale()); 

		        if (gruppo instanceof Viaggio) {
		            Viaggio v = (Viaggio) gruppo; 
		            ps.setDate(3, java.sql.Date.valueOf(v.getDataInizio())); 
		            ps.setDate(4, java.sql.Date.valueOf(v.getDataFine()));
		            ps.setString(5, v.getDestinazione());
		            ps.setInt(6, v.getId()); 
		            
		        } else if (gruppo instanceof Coinquilini) {
		            Coinquilini c = (Coinquilini) gruppo;
		            ps.setInt(3, c.getIndirizzo().getId()); 
		            ps.setInt(4, c.getId());
		            
		        } else if (gruppo instanceof Studio) {
		            Studio s = (Studio) gruppo;
		            ps.setDate(3, java.sql.Date.valueOf(s.getDataEsame()));
		            ps.setString(4, s.getNomeEsame());
		            ps.setInt(5, s.getId());
		            
		        } else {
		            ps.setInt(3, gruppo.getId());
		        }

		        int righeCoinvolte = ps.executeUpdate();
		        if (righeCoinvolte == 0) {
		            throw new RuntimeException("Nessun gruppo trovato con ID: " + gruppo.getId());
		        }

		    } catch (SQLException e) {
		        throw new RuntimeException("Errore nell'aggiornamento del gruppo, " + e.getMessage());
		    }
				
	}

	@Override
	public void eliminaGruppo(Gruppo g) {
		String sql="DELETE FROM Gruppo WHERE idGruppo=?";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, g.getId());
			ps.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella cancellazione del gruppo "+g.getNome());
		}
	}

	@Override
	public ArrayList<Gruppo> tuttiIGruppi() {
		ArrayList<Gruppo> lista= new ArrayList<>();
		String sql="SELECT * FROM Gruppo";
		try(PreparedStatement ps=conn.prepareStatement(sql)){
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Gruppo tmp=new Gruppo(
						rs.getInt("idGruppo"),
						rs.getString("nome"),
						utenteDAO.cercaUtentePerEmail(rs.getString("emailProprietario"))
						);
				lista.add(tmp);
			}
			return lista;
		}
		catch(SQLException e) {
			throw new RuntimeException("Errore nella ricerca dei gruppi,  ");
		}
	}
	

}
