package model;

import java.util.Objects;

public abstract class Movimento {
	private int id;
	
	public Movimento() {};
	
	public Movimento(int id) {
		this.id=id;
	};
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return id;
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
