package com.hackbulgaria.cinema_reservations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	private static SessionFactory factory;

	public static void main(String[] args) {
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Cinema cinema = new Cinema(session);

		cinema.showMovies();

		cinema.showMovieProjections(1);
		
//		cinema.cancelReservation("Maria");
		
		cinema.makeReservation();

		session.close();
	}

}
