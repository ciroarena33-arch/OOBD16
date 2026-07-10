package model;
import java.util.ArrayList;

public class Coinquilini extends Gruppo{

	private Indirizzo indirizzo;
	private ArrayList<Scadenza> scadenze;
	
	public Coinquilini(int id, String nome, Utente proprietario, Indirizzo indirizzo) {
		super(id, nome, proprietario);
		this.indirizzo = indirizzo;
	}

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	public ArrayList<Scadenza> getScadenze() {
		return scadenze;
	}
	
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo=indirizzo;
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