package model;

public class Indirizzo{
	
	private int id;
	private String provincia;
	private String citta;
	private String via;
	private int numCivico;
	
	public Indirizzo(String provincia, String citta, String via, int numCivico) {
		this((Integer)null, provincia, citta, via, numCivico);
	}
	
	public Indirizzo(int id, String provincia, String citta, String via, int numCivico) {
		this.id = id;
		this.provincia = provincia;
		verificaProvincia();
		this.citta = citta;
		this.via = via;
		this.numCivico = numCivico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getCitta() {
		return citta;
	}

	public String getVia() {
		return via;
	}

	public int getNumCivico() {
		return numCivico;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setNumCivico(int numCivico) {
		this.numCivico = numCivico;
	}

	public void verificaProvincia() {
	    if (provincia == null || !provincia.matches("^[A-Z]{2}$")) {
	        throw new RuntimeException("Errore: la provincia deve essere di 2 lettere maiuscole.");
	    }
	}
	
	
	
	
	
	
}