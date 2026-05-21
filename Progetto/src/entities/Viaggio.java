package entities;
import java.util.Date;

public class Viaggio extends Gruppo{
	
	private Date dataInizio;
	private Date dataFine;
	private String destinazione;
	
	public Viaggio(String nome, Utente proprietario, Date dataInizio, Date dataFine, String destinazione) {
		super(nome, proprietario);
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.destinazione = destinazione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	
	

	
	
	
	
	
	
	
}