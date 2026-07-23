package dao;

import java.util.ArrayList;

import model.Notifica;
import model.Utente;

public interface NotificaDAO {
	public void nuovaNotifica(Notifica n);
	public void rispostaNotifica(Notifica n);
	public Notifica cercaNotificaById(int id);
	public ArrayList<Notifica> cercaNotificheInviateByUtente(Utente u);
	public ArrayList<Notifica> cercaNotificheRicevuteByUtente(Utente u);
	public void eliminaNotifica(Notifica n);
}
