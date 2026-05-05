
import java.util.ArrayList;

public class Persona {
	
	private String Nome;
	private String Cognome;
	private String CodiceFiscale;
	private ArrayList<ContoCorrente> Conti;
	
	public int getNumeroConti() {
		return Conti.size();
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public String getNome() {
		return Nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public Persona(String newNome, String newCognome, String newCF) {
		Nome = newNome;
		Cognome = newCognome;
		CodiceFiscale = newCF;
		Conti = new ArrayList<>();
	}
	public boolean aggiungiConto(ContoCorrente Conto) {
		if(Conti.size()<5) {
			for(ContoCorrente clone:Conti) {
				if(clone==Conto) {
					Conto=null;
					return false;
				}
			}
			Conti.add(Conto);
			return true;
		}
		else {
			return false;
		}
	}
	public void rimuoviConto(ContoCorrente Conto) {
		Conti.remove(Conto);
	}
	public void stampaConti() {
		if(Conti.isEmpty()) {
			System.out.println("Nessun conto posseduto.");
		}
		else {
			for(ContoCorrente conto:Conti) {
				Stampe.stampaConto(conto);
			}
		}
	}
	
}
