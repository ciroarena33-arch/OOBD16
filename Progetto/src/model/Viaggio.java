package model;
import java.time.LocalDate;

public class Viaggio extends Gruppo{
	
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String destinazione;
	
	public Viaggio(int id, String nome, Utente proprietario, LocalDate dataInizio, LocalDate dataFine, String destinazione) {
		super(id, nome, proprietario);
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.destinazione = destinazione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	
	

	
	
	
	
	
	
	
}