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
		super(importo);
		this.nomeSpesa = nomeSpesa;
		this.descrizione = descrizione;
		this.data = data;
		this.isComune=isComune;
		this.valuta = valuta;
		this.gruppo = gruppo;
		this.utenteEffettuante = utenteEffettuante;
	}
	
	public Spesa(int id, String nomeSpesa, String descrizione, LocalDate data, double importo, boolean isComune, Valuta valuta, Gruppo gruppo,
			Utente utenteEffettuante) {
		super(id, importo);
		this.nomeSpesa = nomeSpesa;
		this.descrizione = descrizione;
		this.data = data;
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
	
	@Override
	public String toString() {
		return this.nomeSpesa;
	}
	
	
}