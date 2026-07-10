package model;
import java.util.Date;

public class Notifica{
	
	private int id;
	private Debito debito;
	private Date data1;
	private String descrizione1;
	private Date dataRisposta;
	private String descrizioneRisposta;
	
	public Notifica(Debito debito, Date data1, String descrizione1) {
		this.debito = debito;
		this.data1 = data1;
		this.descrizione1 = descrizione1;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public Debito getDebito() {
		return debito;
	}

	public Date getData1() {
		return data1;
	}

	public String getDescrizione1() {
		return descrizione1;
	}

	public Date getDataRisposta() {
		return dataRisposta;
	}

	public String getDescrizioneRisposta() {
		return descrizioneRisposta;
	}

	public void setDebito(Debito debito) {
		this.debito = debito;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public void setDescrizione1(String descrizione1) {
		this.descrizione1 = descrizione1;
	}

	public void setDataRisposta(Date dataRisposta) {
		this.dataRisposta = dataRisposta;
	}

	public void setDescrizioneRisposta(String descrizioneRisposta) {
		this.descrizioneRisposta = descrizioneRisposta;
	}

	
	
	
	
	
	
	
	
}