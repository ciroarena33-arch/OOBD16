public class Stampe {
	public static void stampaIntestatarioEConti(Persona persona) {
		System.out.println(Stampe.stampaIntestatario(persona));
		if(persona.getNumeroConti()==0) {
			System.out.println("Il cliente non ha nessun conto");
		}
		else {
			persona.stampaConti();			
		}
	}
	public static String stampaIntestatario(Persona persona) {
		return "Nome: "+persona.getNome()+". Cognome: "+persona.getCognome()+". Codice Fiscale: "+persona.getCodiceFiscale();
	}
	public static void stampaConto(ContoCorrente conto) {
		System.out.println("Iban: "+conto.getIBAN()+"  Saldo: "+conto.getSaldo());
		System.out.println("Operazioni eseguite:");
		conto.stampaMovimenti();
	}
	public static void stampaTransazione(Movimento transazione) {
		System.out.println("Tipo: "+transazione.getTipo()+"  Importo: "+transazione.getImporto()+"  Data: "+transazione.getData());
		
	}
	
}
