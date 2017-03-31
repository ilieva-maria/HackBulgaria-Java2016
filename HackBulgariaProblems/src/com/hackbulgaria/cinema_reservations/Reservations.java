package com.hackbulgaria.cinema_reservations;

public class Reservations {
	private int id;
	private String username;
	private int row;
	private int col;
	private Projections projection;

	public Reservations() {
	}

	public Reservations(String username, Projections projection, int row, int col) {
		this.username = username;
		this.projection = projection;
		this.row = row;
		this.col = col;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Reservations other = (Reservations) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Projections getProjection() {
		return projection;
	}

	public void setProjection(Projections projection) {
		this.projection = projection;
	}

}
