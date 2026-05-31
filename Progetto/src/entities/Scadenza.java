package entities;
import java.util.Date;

public class Scadenza {
	private String nome;
	private Date dataScadenza;
	
	//possibilità di attivare o disattivere
	//dal punto di vista pratico come verranno usate
	
	public Scadenza(String nome, Date dataScadenza) {
		this.nome = nome;
		this.dataScadenza = dataScadenza;
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

	
	
	
	
}
