package model;

public class Debito extends Movimento{
	
	private Spesa spesa;
	private Utente debitore;
	private double importo;
	private boolean debitoSaldato;

	public Debito(Spesa spesa, Utente debitore, double importo, boolean debitoSaldato) {
		super();
		this.spesa = spesa;
		this.debitore = debitore;
		this.importo = importo;
		this.debitoSaldato = debitoSaldato;
	}
	
	public Debito(int id, Spesa spesa, Utente debitore, double importo, boolean debitoSaldato) {
		super(id);
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

	public void setDebitoSaldato(boolean debitoSaldato) {
		this.debitoSaldato = debitoSaldato;
	}


	
	
	
	
	
	
	
}