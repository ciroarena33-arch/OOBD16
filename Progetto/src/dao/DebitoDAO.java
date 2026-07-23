package dao;

import java.util.ArrayList;

import model.Debito;
import model.Spesa;
import model.Utente;

public interface DebitoDAO {
	public void nuovoDebito(Debito d);
	public void aggiornaDebito(Debito d);
	public Debito cercaDebitoById(int id);
	public ArrayList<Debito> cercaDebitoBySpesa(Spesa s);
	public ArrayList<Debito> cercaDebitoByUtente(Utente u);
	public Debito cercaDebitoUtenteSpesa(Utente u, Spesa s);
	public void eliminaDebito(Debito d);
}
