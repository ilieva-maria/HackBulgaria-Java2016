package thursday;

import java.sql.Time;
import java.util.Date;
//import java.util.HashSet;
import java.util.Set;

public class Projections {
	public static int hallSize = 10;
	private static int[][] hall = new int[hallSize][hallSize];
	private int id;
	private String type;
	private Date date;
	private Time time;
	private Movies movie;
	private Set<Reservations> reservations;

	public Projections() {
	}

	public Projections(String type, Date date, Time time) {
		this.type = type;
		this.date = date;
		this.time = time;
	}
	
	public void updateReservations() {
		for (Reservations reservation : reservations) {
			hall[reservation.getRow()][reservation.getCol()] = 1;
		}
	}
	
	

	public boolean isValid(int row, int col) {
		if (row < 0 || row > 10 || col < 0 || col > 10) {
			System.out.println("The chosen seat is out of range. The hall is " + hallSize + " x " + hallSize);
			return false;
		}
		if (hall[row][col] == 0) {
			return true;
		} else {
			System.out.println("The seat is not available!");
			return false;
		}
	}

	public int availableSeats() {
		return hallSize * hallSize - getReservations().size();
	}

	public void displayHall() {
		updateReservations();
		for (int i = 0; i < hall.length; i++) {
			for (int j = 0; j < hall.length; j++) {
				System.out.print(hall[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
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
		Projections other = (Projections) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Set<Reservations> getReservations() {
		return reservations;
	}
	
	public void setReservations(Set<Reservations> reservations) {
		this.reservations = reservations;
	}
	
	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

}
