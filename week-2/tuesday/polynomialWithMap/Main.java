package polynomialWithMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		p1 = fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8");
		System.out.println(p1.toString());
		p2 = fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8 + 7x^4 + 15x^3");
		OperationsWithPolynomials op = new OperationsWithPolynomials();
		System.out.println(p2.toString());
		p1 = op.multiplyByConstant(p1, 2);
		System.out.println(p1.toString() + " ... p1 multiplied by 2");
		Polynomial p3 = op.add(p1, p2);
		System.out.println(p3.toString() + " ... p1 + p2");

		Polynomial p4 = op.subtract(p1, p2);

		System.out.println(p4.toString() + " ... p1 - p2");
		System.out.println(op.evaluate(p4, 2) + " ... evaluate p4 : x=2");
		System.out.println(op.firstDerivative(p4) + " ... first derivative of p4");
		Polynomial p5 = fromString("2x - 1");
		Polynomial p6 = fromString("3x + 4");
		System.out.println(op.multiply(p5, p6));
		System.out.println(op.multiply(p1, p2));

	}

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
