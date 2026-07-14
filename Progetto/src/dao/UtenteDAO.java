package dao;
import java.util.ArrayList;

import model.*;

public interface UtenteDAO {
	
	void nuovoUtente(Utente u);
	Utente cercaUtentePerEmail(String email);
	void aggiornaUtente(Utente u);
	void eliminaUtente(String email);
	
}
