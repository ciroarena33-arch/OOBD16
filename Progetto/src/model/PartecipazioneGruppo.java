package model;
import java.util.Date;

public class PartecipazioneGruppo{
	
	private Date data;
	private boolean invitoAccettato;
	private Utente utente;
	private Gruppo gruppo;
	
	public PartecipazioneGruppo(Date data, Utente utente, Gruppo gruppo) {
		this.data = data;
		this.invitoAccettato = false;
		this.utente = utente;
		this.gruppo = gruppo;
	}

	public Date getData() {
		return data;
	}

	public boolean isInvitoAccettato() {
		return invitoAccettato;
	}

	public Utente getUtente() {
		return utente;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setInvitoAccettato(boolean invitoAccettato) {
		this.invitoAccettato = invitoAccettato;
	}
	
	
}