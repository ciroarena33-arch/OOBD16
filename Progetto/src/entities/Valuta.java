package entities;

public class Valuta{
	
	private String nome;
	private double conversioneEuro;
	
	public Valuta(String nome, double conversioneEuro) {
		this.nome = nome;
		this.conversioneEuro = conversioneEuro;
	}

	public String getNome() {
		return nome;
	}

	public double getConversioneEuro() {
		return conversioneEuro;
	}

	public void setConversioneEuro(double conversioneEuro) {
		this.conversioneEuro = conversioneEuro;
	}

	
	
	
	
	
	
}