import java.util.ArrayList;

public class ContoCorrente {
	String IBAN;
	private double Saldo;
	private ArrayList<Movimento> Movimenti;
	public ContoCorrente(String IBAN) {
		this.IBAN=IBAN;
		Saldo=0.0;
		Movimenti=new ArrayList<>();
	}
	public double getSaldo() {
		return Saldo;
	}
	public String getIBAN() {
		return IBAN;
	}
	public void deposita(double valore) {
		Saldo+=valore;
	};
	public void ritira(double valore) {
		Saldo-=valore;	
	};
	public void aggiungiMovimento(Movimento transazione) {
		Movimenti.add(transazione);
	}
	public void stampaMovimenti() {
		if(Movimenti.isEmpty()) {
			System.out.println("Nessun conto posseduto.");
		}
		else {
			for(Movimento transazione:Movimenti) {
				Stampe.stampaTransazione(transazione);
			}
		}
	}
}
