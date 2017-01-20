package thursday;

import java.util.Set;

public class Movies {
	private int id;
	private String name;
	private double rating;
	private Set<Projections> projections;

	public Movies() {
	}

	public Movies(String name, double rating) {
		super();
		this.name = name;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<Projections> getProjections() {
		return projections;
	}

	public void setProjections(Set<Projections> projections) {
		this.projections = projections;
	}
}
