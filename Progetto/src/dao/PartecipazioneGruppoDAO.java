package dao;
import java.util.ArrayList;

import model.Gruppo;
import model.PartecipazioneGruppo;
import model.Utente;
public interface PartecipazioneGruppoDAO {
	void nuovaPartecipazione(PartecipazioneGruppo p);
	void aggiornaPartecipazione(PartecipazioneGruppo p);
	ArrayList<PartecipazioneGruppo> cercaPartecipazioniByUtenteId(Utente u);
	ArrayList<PartecipazioneGruppo> cercaPartecipazioniByGruppoId(Gruppo g);
	PartecipazioneGruppo getPartecipazione(Utente u, Gruppo g);
	void eliminaPartecipazione(PartecipazioneGruppo p);

}
