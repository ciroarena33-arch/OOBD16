public class Starter {

	public static void main(String[] args) {

		Persona p1=new Persona("Ciro", "Arena", "RNACRI06E02G190Z");
		ContoCorrente c1=new ContoCorrente("IT01");
		ContoCorrente c2=new ContoCorrente("IT02");
		ContoCorrente c3=new ContoCorrente("IT03");
		ContoCorrente c4=new ContoCorrente("IT04");
		ContoCorrente c5=new ContoCorrente("IT05");

		Operazioni.associaConto(p1, c1);
		Operazioni.associaConto(p1, c2);
		Operazioni.associaConto(p1, c3);
		Operazioni.associaConto(p1, c4);
		Operazioni.associaConto(p1, c5);

		Operazioni.effettuaDeposito(c1, 1800);
		Operazioni.effettuaDeposito(c2, 3400);
		Operazioni.effettuaDeposito(c3, 2300);
		Operazioni.effettuaDeposito(c4, 1200);
		Operazioni.effettuaDeposito(c5, 2200);
		
		Operazioni.effettuaBonifico(c1, c2, 1700);
		Operazioni.effettuaRitiro(c2, 1700);
		Operazioni.effettuaRitiro(c4, 1300);
		Stampe.stampaIntestatarioEConti(p1);		
		
	}

}
