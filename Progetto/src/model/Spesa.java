package model;
import java.util.Date;

public class Spesa{
	
	private String nomeSpesa;
	private String descrizione;
	private Date data;
	private double importo;
	private Valuta valuta;
	private Gruppo gruppo;
	private Utente utenteEffettuante;
	
	public Spesa(String nomeSpesa, String descrizione, Date data, double importo, Valuta valuta, Gruppo gruppo,
			Utente utenteEffettuante) {
		this.nomeSpesa = nomeSpesa;
		this.descrizione = descrizione;
		this.data = data;
		this.importo = importo;
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

	public Date getData() {
		return data;
	}

	public double getImporto() {
		return importo;
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

	public void setData(Date data) {
		this.data = data;
	}





	
	
	
	
	
	
	
	
}