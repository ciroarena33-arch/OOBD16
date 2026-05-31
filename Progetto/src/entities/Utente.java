package entities;
import java.util.ArrayList;

public class Utente{

private String emailIstituzionale;
private String nome;
private String cognome;
private String password;
private String telefono;
private ArrayList<Gruppo> gruppi;



//Lista spese effettuate dubbio: movimenti specializza spese e debiti? cosi da fare un unico array



//Costruttore con e senza telefono,

public Utente(String emailIstituzionale, String nome, String cognome, String password, String telefono,
		ArrayList<Gruppo> gruppi) {
	this.emailIstituzionale = emailIstituzionale;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.telefono = telefono;
	this.gruppi=new ArrayList<>();
}

public Utente(String emailIstituzionale, String nome, String cognome, String password) {
	this.emailIstituzionale = emailIstituzionale;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.gruppi=new ArrayList<>();
}


public String getEmailIstituzionale() {
	return emailIstituzionale;
}

public String getNome() {
	return nome;
}

public String getCognome() {
	return cognome;
}

public String getPassword() {
	return password;
}

public String getTelefono() {
	return telefono;
}

public ArrayList<Gruppo> getGruppi() {
	return gruppi;
}

public void setNome(String nome) {
	this.nome = nome;
}

public void setCognome(String cognome) {
	this.cognome = cognome;
}

public void setPassword(String password) {
	this.password = password;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}


public void addGruppo(Gruppo nuovoGruppo){
	if(gruppi.contains(nuovoGruppo)){
		throw new IllegalArgumentException("L'Utente già appartiene al gruppo");
	}
	else{
		gruppi.add(nuovoGruppo);
	}
}

public void removeGruppo(Gruppo nuovoGruppo){
	if(gruppi.contains(nuovoGruppo)){
		throw new IllegalArgumentException("L'Utente non appartiene al gruppo");
	}
	else{
		gruppi.remove(nuovoGruppo);
	}
}

}