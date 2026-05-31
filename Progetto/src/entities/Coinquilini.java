package entities;
import java.util.ArrayList;

public class Coinquilini extends Gruppo{

	private Indirizzo indirizzo;
	private ArrayList<Scadenza> scadenze;
	
	public Coinquilini(String nome, Utente proprietario, Indirizzo indirizzo, ArrayList<Scadenza> scadenze) {
		super(nome, proprietario);
		this.indirizzo = indirizzo;
		this.scadenze = scadenze;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	public ArrayList<Scadenza> getScadenze() {
		return scadenze;
	}
	public void addScadenza(Scadenza scadenza){
		if(scadenze.contains(scadenza)){
			return;
		}
		else{
			scadenze.add(scadenza);
		}
	}
public void removeScadenza(Scadenza scadenza){
		if(scadenze.contains(scadenza)){
			scadenze.remove(scadenza);
		}
		else{
			return;
	}
	
	}

	
	
	
	
	
	
	




}