package dao;
import java.util.ArrayList;

import model.*;

public interface UtenteDAO {
	
	void nuovoUtente(Utente u);
	Utente cercaUtentePerEmail(String email);
	void aggiornaUtente(String email, String password, String nome, String cognome, long telefono);
	void eliminaUtente(String email);
	ArrayList<Utente> tuttiGliUtenti();
}
