package com.hackbulgaria.polynomial;

public class UserInput {

	public static Polynomial fromString(String polynomial) {
		Polynomial polynom = new Polynomial();
		polynomial = polynomial.replaceAll("\\s+", "").replaceAll("-", "+-");
		String[] monomials = polynomial.split("\\+");
		for (String monomial : monomials) {
			int[] m = parseMonomial(monomial);
			polynom.add(m[0], m[1]);

		}
		return polynom;
	}

	public static int[] parseMonomial(String monomial) {
		int[] m = new int[2];
		int coefficient = 0;
		int degree = 0;
		if (monomial.contains("x")) {
			monomial = monomial.replaceAll("x", "");
			String[] monomialParts = monomial.split("\\^");
			coefficient = Integer.parseInt(monomialParts[0]);
			if (monomialParts.length > 1) {
				degree = Integer.parseInt(monomialParts[1]);
			} else {
				degree = 1;
			}
		} else {
			coefficient = Integer.parseInt(monomial);
		}
		m[0] = coefficient;
		m[1] = degree;
		return m;

	}

}
