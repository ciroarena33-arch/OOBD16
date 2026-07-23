package model;
import java.util.ArrayList;

public class Utente{

private String emailIstituzionale;
private String nome;
private String cognome;
private String password;
private String telefono;
private ArrayList<PartecipazioneGruppo> partecipazioniGruppi;
private ArrayList<Movimento> movimenti;

public Utente(String emailIstituzionale, String nome, String cognome, String password) {
	
	this(emailIstituzionale, nome, cognome, password, null);
	
}

public Utente(String emailIstituzionale, String nome, String cognome, String password, String telefono) {
	
	validaEmailIstituzionale(emailIstituzionale);
	validaPassword(password);
	validaTelefono(telefono);
	
	this.emailIstituzionale = emailIstituzionale;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
	this.telefono = telefono;
	
	this.partecipazioniGruppi = new ArrayList<>();
	this.movimenti = new ArrayList<>();
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

public ArrayList<PartecipazioneGruppo> getPartecipazioniGruppi() {
	return partecipazioniGruppi;
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

public ArrayList<Movimento> getMovimenti() {
	return movimenti;
}

public void accessoValido(String mail, String password) {
	if(!this.password.equals(password)) {
		throw new RuntimeException("Password non valida");
	}
}

public void addGruppo(PartecipazioneGruppo nuovoGruppo){
	for(PartecipazioneGruppo p:partecipazioniGruppi) {
		if(p.getGruppo()==nuovoGruppo.getGruppo()){
			return;
		}
	}
	partecipazioniGruppi.add(nuovoGruppo);
}

public void removeGruppo(Gruppo nuovoGruppo){
	for(PartecipazioneGruppo p:partecipazioniGruppi) {
		if(p.getGruppo()==nuovoGruppo){
			partecipazioniGruppi.remove(p);
			return;
		}
	}
	throw new IllegalArgumentException("L'Utente non appartiene al gruppo");
}	

public void validaEmailIstituzionale(String emailIstituzionale) {
	if (!emailIstituzionale.contains("@")) {
		throw new RuntimeException("La mail inserita non contiene il simbolo @");
	}
	else if(!emailIstituzionale.contains("@unina.it")&&!emailIstituzionale.contains("@studenti.unina.it")) {
		throw new RuntimeException("La mail inserita non è istituzionale della Federico II");
	}
}

public void validaPassword(String password) {
	if(password.length()<8) {
		throw new RuntimeException("La password deve contenere almeno 8 caratteri");
	}
	if (!password.matches(".*[A-Z].*")) {
        throw new RuntimeException("La password deve contenere almeno una lettera maiuscola.");
    }
	if (!password.matches(".*\\d.*")) {
        throw new RuntimeException("La password deve contenere almeno un numero.");
    }
	if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
        throw new RuntimeException("La password deve contenere almeno un carattere speciale (es. !, @, #, $, ecc.).");
    }
}

public void validaTelefono(String telefono) {
	if(telefono==null) {
		return;
	}
	if (!telefono.matches("\\d+")) {
        throw new RuntimeException("Il numero di telefono deve contenere solo numeri.");
    }
    if (telefono.length() != 10) {
        throw new RuntimeException("Il numero di telefono deve essere lungo esattamente 10 cifre.");
    }
}

public String toString() {
	return this.cognome+" "+this.nome;
}

}