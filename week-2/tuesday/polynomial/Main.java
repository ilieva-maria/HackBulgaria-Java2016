package polynomial;

public class Main {
	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		p1 = fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8");
		
		OperationsWithPolynomials op = new OperationsWithPolynomials();
		p1 = op.multiplyByConstant(p1, 2);
		System.out.println(p1.toString());
		p2 = fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8 + 7x^4 + 15x^3");
		System.out.println();
		p1.LexicographicalOrder();
		p2.LexicographicalOrder();
		System.out.println(p2);
		System.out.println(p1.toString() + " ...p1 before ");
		System.out.println(p2.toString() + " ...p2 before ");
		Polynomial p3 = op.add(p1, p2);
		System.out.println(p3.toString() + " ... p1 + p2");
		System.out.println(p1.toString() + " ...p1 after");
		System.out.println(p2.toString() + " ...p2 after ");
		System.out.println(p1 + " ... p1 before subtract");
		System.out.println(p2 + " ... p2 before subtract");
		Polynomial p4 = op.subtract(p1, p2);
		
		System.out.println(p4.toString());
		System.out.println(p1 + " ... p1 aft subtract");
		System.out.println(p2 + " ... p2 aft subtract");
//		Monomial<Number, Integer> m = new Monomial<Number, Integer>(3, 10);
//		op.addMonomial(p2, m);
		
	}
	
	public static Polynomial fromString(String polynomial) {
		Polynomial polynom = new Polynomial();
		polynomial = polynomial.replaceAll("\\s+", "").replaceAll("-", "+-");
		String[] monomials = polynomial.split("\\+");
		for (String monomial : monomials) {
			Monomial<Number, Integer> m = parseMonomial(monomial);
			polynom.add(m);
			
		}
		System.out.println(polynom.toString());
		return polynom;
	}
	
	public static Monomial<Number, Integer> parseMonomial(String monomial){
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
		Monomial<Number, Integer> m = new Monomial<Number, Integer>(coefficient, degree);
		return m;
		
	}
	
	
}
