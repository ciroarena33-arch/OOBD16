package model;

import java.util.Objects;

public abstract class Movimento {
	private int id;
	private double importo;
	
	public Movimento(double importo) {
		this.importo=importo;
	};
	
	public Movimento(int id, double importo) {
		this.id=id;
		this.importo=importo;
	};
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	
	public double getImporto() {
		return importo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimento other = (Movimento) obj;
		return id == other.id;
	}
	
	
}
