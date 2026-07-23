package dao;

import java.util.ArrayList;

import model.Coinquilini;
import model.Scadenza;

public interface ScadenzaDAO {
	public void nuovaScadenza(Scadenza s);
	public void aggiornaScadenza(Scadenza s);
	public Scadenza cercaScadenzaById(int id);
	public ArrayList<Scadenza> cercaScadenzePerGruppo(Coinquilini g);
	public void eliminaScadenza(Scadenza s);
	
}
