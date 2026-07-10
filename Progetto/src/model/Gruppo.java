package model;
import java.util.ArrayList;

public class Gruppo{
	
	private int id;
	private String nome;
	private ArrayList<PartecipazioneGruppo> componenti;
	private Utente proprietario;
	private ArrayList<Spesa> spese;
	
	public Gruppo(int id,String nome, Utente proprietario) {
		this.id=id;
		this.nome = nome;
		this.proprietario = proprietario;
		this.componenti=new ArrayList<PartecipazioneGruppo>();
		this.spese=new ArrayList<Spesa>();
	}
	
	public Gruppo(String nome, Utente proprietario) {
		this((Integer)null, nome, proprietario);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getProprietario() {
		return proprietario;
	}

	public void setProprietario(Utente proprietario) {
		this.proprietario = proprietario;
	}

	public ArrayList<PartecipazioneGruppo> getComponenti() {
		return componenti;
	}

	public void addComponente(PartecipazioneGruppo componente) {
		if (componenti.contains(componente)) {
			throw new IllegalArgumentException("Il componente appartiene già al gruppo");
		}
		componenti.add(componente);
	}

	public void removeComponente(PartecipazioneGruppo componente) {
		if (!componenti.contains(componente)) {
			throw new IllegalArgumentException("Il componente non fa parte del gruppo");
		}
		componenti.remove(componente);
	}
}
