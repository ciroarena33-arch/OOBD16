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
	this.emailIstituzionale = emailIstituzionale;
	this.nome = nome;
	this.cognome = cognome;
	this.password = password;
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


public void addGruppo(PartecipazioneGruppo nuovoGruppo){
	for(PartecipazioneGruppo p:partecipazioniGruppi) {
		if(p.getGruppo()==nuovoGruppo.getGruppo()){
			throw new IllegalArgumentException("L'Utente già appartiene al gruppo");
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
	
	

}