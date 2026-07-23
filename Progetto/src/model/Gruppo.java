package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Gruppo{
	
	private int id;
	private String nome;
	private ArrayList<PartecipazioneGruppo> componenti;
	private Utente proprietario;
	
	private LocalDate dataCreazione;
	private ArrayList<Spesa> spese;
	
	public Gruppo(int id,String nome, Utente proprietario, LocalDate dataCreazione) {
		this.id=id;
		this.nome = nome;
		this.proprietario = proprietario;
		this.componenti=new ArrayList<PartecipazioneGruppo>();
		this.dataCreazione=dataCreazione;
		this.spese=new ArrayList<Spesa>();
	}
	
	public Gruppo(String nome, Utente proprietario, LocalDate dataCreazione) {
		this.nome = nome;
		this.proprietario = proprietario;
		this.componenti=new ArrayList<PartecipazioneGruppo>();
		this.dataCreazione=LocalDate.now();
		this.spese=new ArrayList<Spesa>();
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
	
	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void addComponente(PartecipazioneGruppo componente) {
		for(PartecipazioneGruppo p:componenti) {
			if (p.equals(componente)) {
				return;
			}
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
