package entities;

public class Indirizzo{
	
	private String provincia;
	private String citta;
	private String via;
	private int numCivico;
	
	public Indirizzo(String provincia, String citta, String via, int numCivico) {
		this.provincia = provincia;
		this.citta = citta;
		this.via = via;
		this.numCivico = numCivico;
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

	
	
	
	
	
	
}