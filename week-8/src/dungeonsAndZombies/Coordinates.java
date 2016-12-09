package dungeonsAndZombies;

public class Coordinates {
	private int rowPosition;
	private int colPosition;
	
	public Coordinates(int rowPosition, int colPosition) {
		super();
		this.rowPosition = rowPosition;
		this.colPosition = colPosition;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colPosition;
		result = prime * result + rowPosition;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (colPosition != other.colPosition)
			return false;
		if (rowPosition != other.rowPosition)
			return false;
		return true;
	}


	public int getRowPosition() {
		return rowPosition;
	}
	public void setRowPosition(int rowPosition) {
		this.rowPosition = rowPosition;
	}
	public int getColPosition() {
		return colPosition;
	}
	public void setColPosition(int colPosition) {
		this.colPosition = colPosition;
	}
	
}
