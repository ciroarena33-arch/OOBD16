public class Movimento {
private String Tipo;
private double Importo;
private String Data;

public Movimento(String Tipo, double Importo, String Data) {
	this.Tipo=Tipo;
	this.Importo=Importo;
	this.Data=Data;
}
public String getTipo() {
	return Tipo;
}
public double getImporto() {
	return Importo;
}
public String getData() {
	return Data;
}
}
