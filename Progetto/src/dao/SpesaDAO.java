package dao;

import java.util.ArrayList;

import model.Gruppo;
import model.Spesa;
import model.Utente;

public interface SpesaDAO {
	public void nuovaSpesa(Spesa s);
	public void aggiornaSpesa(Spesa s);
	public Spesa cercaSpesaById(int id);
	public ArrayList<Spesa> cercaSpesaByGruppo(Gruppo g, Utente u);
	public void eliminaSpesa(Spesa s);
}
