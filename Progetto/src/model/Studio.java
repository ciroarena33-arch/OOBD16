package model;
import java.time.LocalDate;

public class Studio extends Gruppo{
	
	private String nomeEsame;
	private LocalDate dataEsame;
	
	public Studio(int id, String nome, Utente proprietario, String nomeEsame, LocalDate dataEsame) {
		super(id, nome, proprietario);
		this.nomeEsame = nomeEsame;
		this.dataEsame = dataEsame;
	}

	public String getNomeEsame() {
		return nomeEsame;
	}

	public void setNomeEsame(String nomeEsame) {
		this.nomeEsame = nomeEsame;
	}

	public LocalDate getDataEsame() {
		return dataEsame;
	}

	public void setDataEsame(LocalDate dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	
	
	
	
	
	
	
	
}