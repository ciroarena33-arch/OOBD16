public class Operazioni {
	public static boolean effettuaDeposito(ContoCorrente conto, double valore) {
		if(valore>0) {
			conto.deposita(valore);
			Movimento m=new Movimento("Deposito", valore, "data");
			conto.aggiungiMovimento(m);
			return true;
		}
		else {
			return false;
		}
	}	
	public static boolean effettuaRitiro(ContoCorrente conto, double valore) {
		if(valore>0&&valore<=conto.getSaldo()) {
			conto.deposita(valore);
			Movimento m=new Movimento("Ritiro", valore, "data");
			conto.aggiungiMovimento(m);
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean effettuaBonifico(ContoCorrente contoDa, ContoCorrente contoA, double valore) {
		if(contoDa.getSaldo()>=valore) {
			contoDa.ritira(valore);
			Movimento BonificoInviato=new Movimento("Bonifico inviato", valore, "data");
			contoDa.aggiungiMovimento(BonificoInviato);
			contoA.deposita(valore);
			Movimento BonificoRicevuto=new Movimento("Bonifico Ricevuto", valore, "data");
			contoA.aggiungiMovimento(BonificoRicevuto);
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean associaConto(Persona persona, ContoCorrente conto) {
		return persona.aggiungiConto(conto);
	}
}

