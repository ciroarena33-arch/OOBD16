package entities;

public class Debito{
	
	private Spesa spesa;
	private Utente debitore;
	private double importo;
	private boolean debitoSaldato;
	private Notifica notificaDiSollecito;

	public Debito(Spesa spesa, Utente debitore, double importo, boolean debitoSaldato) {
		this.spesa = spesa;
		this.debitore = debitore;
		this.importo = importo;
		this.debitoSaldato = debitoSaldato;
	}

	public Spesa getSpesa() {
		return spesa;
	}

	public Utente getDebitore() {
		return debitore;
	}

	public double getImporto() {
		return importo;
	}

	public boolean isDebitoSaldato() {
		return debitoSaldato;
	}

	public Notifica getNotificaDiSollecito() {
		return notificaDiSollecito;
	}

	public void setSpesa(Spesa spesa) {
		this.spesa = spesa;
	}

	public void setDebitoSaldato(boolean debitoSaldato) {
		this.debitoSaldato = debitoSaldato;
	}

	public void setNotificaDiSollecito(Notifica notificaDiSollecito) {
		this.notificaDiSollecito = notificaDiSollecito;
	}

	
	
	
	
	
	
	
}