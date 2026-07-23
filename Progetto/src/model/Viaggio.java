package model;
import java.time.LocalDate;

public class Viaggio extends Gruppo{
	
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String destinazione;
	
	public Viaggio(String nome, Utente proprietario, LocalDate dataCreazione, LocalDate dataInizio, LocalDate dataFine, String destinazione) {
		super(nome, proprietario, dataCreazione);
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.destinazione = destinazione;
		
	}

	public Viaggio(int id, String nome, Utente proprietario,LocalDate dataCreazione, LocalDate dataInizio, LocalDate dataFine, String destinazione) {
		super(id, nome, proprietario, dataCreazione);
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
	
	public void verificaDate() {
		if(dataInizio.isAfter(dataFine)) {
			throw new RuntimeException("Le date inserite non sono valide: La data di partenza è successiva alla data di ritorno");
		}
	}
	

	
	
	
	
	
	
	
}