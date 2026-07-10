package model;
import java.time.LocalDate;

public class PartecipazioneGruppo{
	
	private int id;
	private LocalDate data;
	private boolean invitoAccettato;
	private Utente utente;
	private Gruppo gruppo;
	
	public PartecipazioneGruppo(LocalDate data, Utente utente, Gruppo gruppo) {
		this(-1, data, false, utente, gruppo);
	}
	
	public PartecipazioneGruppo(int id,LocalDate data, boolean invitoAccettato, Utente utente, Gruppo gruppo) {
		this.id=id;
		this.data = data;
		this.invitoAccettato = invitoAccettato;
		this.utente = utente;
		this.gruppo = gruppo;
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getData() {
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

	public void setId(int id) {
		this.id=id;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setInvitoAccettato(boolean invitoAccettato) {
		this.invitoAccettato = invitoAccettato;
	}
	
	
}