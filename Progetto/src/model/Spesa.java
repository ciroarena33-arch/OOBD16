package model;

import java.time.LocalDate;

public class Spesa extends Movimento{
	
	private String nomeSpesa;
	private String descrizione;
	private LocalDate data;
	private double importo;
	private boolean isComune;
	private Valuta valuta;
	private Gruppo gruppo;
	private Utente utenteEffettuante;
	
	public Spesa(String nomeSpesa, String descrizione, LocalDate data, double importo, boolean isComune, Valuta valuta, Gruppo gruppo,
			Utente utenteEffettuante) {
		this.nomeSpesa = nomeSpesa;
		this.descrizione = descrizione;
		this.data = data;
		this.importo = importo;
		this.isComune=isComune;
		this.valuta = valuta;
		this.gruppo = gruppo;
		this.utenteEffettuante = utenteEffettuante;
	}
	
	public Spesa(int id, String nomeSpesa, String descrizione, LocalDate data, double importo, boolean isComune, Valuta valuta, Gruppo gruppo,
			Utente utenteEffettuante) {
		super(id);
		this.nomeSpesa = nomeSpesa;
		this.descrizione = descrizione;
		this.data = data;
		this.importo = importo;
		this.isComune=isComune;
		this.valuta = valuta;
		this.gruppo = gruppo;
		this.utenteEffettuante = utenteEffettuante;
	}
	
	public String getNomeSpesa() {
		return nomeSpesa;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public double getImporto() {
		return importo;
	}

	public boolean isComune() {
		return isComune;
	}
	
	public Valuta getValuta() {
		return valuta;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	public Utente getUtenteEffettuante() {
		return utenteEffettuante;
	}
	
	public void setNomeSpesa(String nomeSpesa) {
		this.nomeSpesa = nomeSpesa;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setValuta(Valuta valuta) {
		this.valuta=valuta;
	}





	
	
	
	
	
	
	
	
}