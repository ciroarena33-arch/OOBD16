package entities;
import java.util.Date;

public class PartecipazioneGruppo{
	
	private Date data;
	private boolean invitoAccettato;
	private Utente utente;
	private Gruppo gruppo;
	
	public PartecipazioneGruppo(Date data, boolean invitoAccettato, Utente utente, Gruppo gruppo) {
		this.data = data;
		this.invitoAccettato = invitoAccettato;
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

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo = gruppo;
	}

	
	
	
	
	
}