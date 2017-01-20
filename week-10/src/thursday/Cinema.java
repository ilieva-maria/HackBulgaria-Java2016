package thursday;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Cinema {
	private Session session;

	public Cinema(Session session) {
		this.session = session;
	}

	public void showMovies() {
		List<?> movies = session.createQuery("FROM Movies").getResultList();
		for (Iterator<?> iterator = movies.iterator(); iterator.hasNext();) {
			Movies movie = (Movies) iterator.next();
			System.out.print("ID: " + movie.getId());
			System.out.print("  Name: " + movie.getName());
			System.out.println("  Rating: " + movie.getRating());
		}
	}

	public void showMovieProjections(int movie_id) {
		@SuppressWarnings("unchecked")
		List<Projections> projections = session.createQuery("FROM Projections").getResultList();
		projections = projections.stream().filter(proj -> movie_id == ((Projections) proj).getMovie().getId())
				.collect(Collectors.toList());
		Collections.sort(projections, new Comparator<Projections>() {

			@Override
			public int compare(Projections o1, Projections o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		for (Projections projection : projections) {
			System.out.print("ID: " + projection.getId());
			System.out.print("  Movie: " + projection.getMovie().getName());
			System.out.print("  Type: " + projection.getType());
			System.out.print("  Date: " + projection.getDate());
			System.out.print("  Time: " + projection.getTime());
			System.out.println("  Total seats available: " + projection.availableSeats());
		}
	}

	public Integer addReservation(String username, Projections projection, int row, int col) {
		Reservations reservation = new Reservations(username, projection, row, col);
		int reservationID = (Integer) session.save(reservation);
		return reservationID;

	}

	public void makeReservation(String username, Projections projection, int numberOfTickets, int[] rowNumbers,
			int[] colNumbers) {
		projection.updateReservations();
		for (int i = 0; i < numberOfTickets; i++) {
			addReservation(username, projection, rowNumbers[i], colNumbers[i]);
		}
		projection.updateReservations();

	}

	public void makeReservation() {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter your name :");
			String username = scanner.nextLine().trim();
			showMovies();
			System.out.println("Please enter the ID of the desired movie and amount of tickets :");
			int movieID = scanner.nextInt();
			int numberOfTickets = scanner.nextInt();
			showMovieProjections(movieID);
			System.out.println("Please enter the ID of the desired projection:");
			int projectionID = scanner.nextInt();
			Projections projection = (Projections) session.get(Projections.class, projectionID);
			if (projection != null && projection.availableSeats() <= numberOfTickets) {
				System.out.println("Sorry, there are not enough available seats for your reservation.");
			} else {
				projection.displayHall();
				int rowNumbers[] = new int[numberOfTickets];
				int colNumbers[] = new int[numberOfTickets];
				for (int i = 0; i < numberOfTickets; i++) {
					while (true) {
						System.out.println("Please choose row and column:");
						int row = scanner.nextInt();
						int col = scanner.nextInt();
						if (projection.isValid(row, col)) {
							rowNumbers[i] = row;
							colNumbers[i] = col;
							break;
						}
					}
				}
				System.out.println("This is your reservation: ");
				System.out.println("Movie: " + projection.getMovie().getName());
				System.out.print("Seats: ");
				for (int i = 0; i < numberOfTickets; i++) {
					System.out.print("(" + rowNumbers[i] + ", " + colNumbers[i] + ")");
				}
				System.out.println("Please confirm your reservation by typing 'yes' : ");
				scanner.nextLine();
				String confirmation = scanner.nextLine();
				if (confirmation.equals("yes")) {
					makeReservation(username, projection, numberOfTickets, rowNumbers, colNumbers);
					System.out.println("Thanks.");
					projection.displayHall();
					scanner.close();
					tx.commit();
				} else {
					scanner.close();
				}

			}

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		}
	}

	public void cancelReservation(String username) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<?> reservations = session.createQuery("FROM Reservations where username like " + "'" + username + "'").getResultList();
			for (Iterator<?> iterator = reservations.iterator(); iterator.hasNext();) {
				Reservations reservation = (Reservations) iterator.next();
				session.delete(reservation);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();

			}
		}
	}
}
