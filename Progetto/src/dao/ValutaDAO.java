package dao;

import java.util.ArrayList;

import model.Valuta;

public interface ValutaDAO {
	public Valuta cercaValuta(String nome);
	public ArrayList<Valuta> tutteLeValute();
}
