package gui.gruppo;

public class ListaGruppi {
	int id;
	String nome;
	
	public ListaGruppi(int id, String nome) {
		this.id=id;
		this.nome=nome;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
		return this.nome;
	}
}
