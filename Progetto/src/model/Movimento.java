package model;

public abstract class Movimento {
	private int id;
	
	public Movimento() {};
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
}
