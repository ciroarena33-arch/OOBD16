package model;
import java.util.Date;

public class Studio extends Gruppo{
	
	private String nomeEsame;
	private Date dataEsame;
	
	public Studio(String nome, Utente proprietario, String nomeEsame, Date dataEsame) {
		super(nome, proprietario);
		this.nomeEsame = nomeEsame;
		this.dataEsame = dataEsame;
	}

	public String getNomeEsame() {
		return nomeEsame;
	}

	public void setNomeEsame(String nomeEsame) {
		this.nomeEsame = nomeEsame;
	}

	public Date getDataEsame() {
		return dataEsame;
	}

	public void setDataEsame(Date dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	
	
	
	
	
	
	
	
}