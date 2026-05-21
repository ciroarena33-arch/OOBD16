package entities;
import java.util.ArrayList;

public class Gruppo{
	
	private String nome;
	private ArrayList<PartecipazioneGruppo> componenti;
	private Utente proprietario;
	
	public Gruppo(String nome, Utente proprietario) {
		this.nome = nome;
		this.proprietario = proprietario;
		this.componenti=new ArrayList<PartecipazioneGruppo>();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
