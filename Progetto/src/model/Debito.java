package model;

public class Debito extends Movimento{
	
	private Spesa spesa;
	private Utente debitore;
	private boolean debitoSaldato;

	public Debito(Spesa spesa, Utente debitore, double importo, boolean debitoSaldato) {
		super(importo);
		this.spesa = spesa;
		this.debitore = debitore;
		this.debitoSaldato = debitoSaldato;
	}
	
	public Debito(int id, Spesa spesa, Utente debitore, double importo, boolean debitoSaldato) {
		super(id, importo);
		this.spesa = spesa;
		this.debitore = debitore;
		this.debitoSaldato = debitoSaldato;
	}

	public Spesa getSpesa() {
		return spesa;
	}

	public Utente getDebitore() {
		return debitore;
	}

	public boolean isDebitoSaldato() {
		return debitoSaldato;
	}

	public void setDebitoSaldato(boolean debitoSaldato) {
		this.debitoSaldato = debitoSaldato;
	}


	
	
	
	
	
	
	
}