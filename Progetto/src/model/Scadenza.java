package model;
import java.util.Date;

public class Scadenza {
	private String nome;
	private Date dataScadenza;
	private double importo;
	
	
	public Scadenza(String nome, Date dataScadenza, double importo) {
		this.nome = nome;
		this.dataScadenza = dataScadenza;
		this.importo=importo;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public void setImporto(double importo) {
		this.importo=importo;
	}
	
	
	
	
}
