package dao;

import model.Gruppo;
import java.util.ArrayList;

public interface GruppoDAO {
	void inserisciGruppo(Gruppo g);
	Gruppo cercaGruppoById(int id);
	void aggiornaGruppo(Gruppo gruppo);
	void eliminaGruppo(Gruppo g);
	ArrayList<Gruppo> tuttiIGruppi();
}
