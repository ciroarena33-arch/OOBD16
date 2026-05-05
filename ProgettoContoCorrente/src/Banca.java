import java.util.ArrayList;

public class Banca {
private ArrayList<Persona> Cliente;

public boolean nuovoCliente(Persona persona) {
	for(Persona cliente:Cliente) {
		if(cliente.getCodiceFiscale()==persona.getCodiceFiscale()) {
			return false;
		}
	}
	Cliente.add(persona);
	return true;
}
public Persona trovaCliente(String CodiceFiscale) {
	for(Persona cliente:Cliente) {
		if(cliente.getCodiceFiscale()==CodiceFiscale) {
			return cliente;
		}
	}
	return null;
}
}
