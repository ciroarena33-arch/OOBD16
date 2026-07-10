package dao;

import model.Indirizzo;

public interface IndirizzoDAO {
	public void nuovoIndirizzo(Indirizzo i);
	public Indirizzo cercaIndirizzoById(int id);
	public void aggiornaIndirizzo(Indirizzo i);
	public void eliminaIndirizzo(int id);
}
