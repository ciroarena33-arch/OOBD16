package model;
import java.time.LocalDate;

public class Notifica{
	
	private int id;
	private Debito debito;
	private LocalDate data1;
	private String descrizione1;
	private LocalDate dataRisposta;
	private String descrizioneRisposta;
	
	public Notifica(Debito debito, LocalDate data1, String descrizione1) {
		this.debito = debito;
		this.data1 = data1;
		this.descrizione1 = descrizione1;
	}

	public Notifica(int id, Debito debito, LocalDate data1, String descrizione1, LocalDate dataRisposta,
			String descrizioneRisposta) {
		this.id = id;
		this.debito = debito;
		this.data1 = data1;
		this.descrizione1 = descrizione1;
		this.dataRisposta = dataRisposta;
		this.descrizioneRisposta = descrizioneRisposta;
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

	public LocalDate getData1() {
		return data1;
	}

	public String getDescrizione1() {
		return descrizione1;
	}

	public LocalDate getDataRisposta() {
		return dataRisposta;
	}

	public String getDescrizioneRisposta() {
		return descrizioneRisposta;
	}

	public void setDebito(Debito debito) {
		this.debito = debito;
	}

	public void setData1(LocalDate data1) {
		this.data1 = data1;
	}

	public void setDescrizione1(String descrizione1) {
		this.descrizione1 = descrizione1;
	}

	public void setDataRisposta(LocalDate dataRisposta) {
		this.dataRisposta = dataRisposta;
	}

	public void setDescrizioneRisposta(String descrizioneRisposta) {
		this.descrizioneRisposta = descrizioneRisposta;
	}

	
	
	
	
	
	
	
	
}