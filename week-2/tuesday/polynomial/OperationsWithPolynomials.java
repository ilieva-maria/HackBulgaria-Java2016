package polynomial;

//import java.util.Vector;

public class OperationsWithPolynomials {
	public Polynomial multiplyByConstant(Polynomial polynomial, int constant) {
		Polynomial result = new Polynomial();
		for(Monomial<Number, Integer> m : polynomial.getPolynom()){
			Monomial<Number, Integer> newMonomial = new Monomial<Number, Integer>(m.getCoefficient().doubleValue() * constant, m.getDegree());
			result.add(newMonomial);
			//result = addMonomial(result, newMonomial);
		}
		return result;
	}
	
//	public Polynomial addMonomial(Polynomial polynomial, Monomial<Number, Integer> monomial) {
//		Polynomial result = new Polynomial();
//		boolean containsDegree = false;
//		for (Monomial<Number, Integer> m : polynomial.getPolynom()) {
//			if (m.compareTo(monomial) == 0) {
//				Number newCoefficient = m.getCoefficient().doubleValue() + monomial.getCoefficient().doubleValue();
//				if (newCoefficient.doubleValue() == 0) {
//					continue;
//				} else {
//					Monomial<Number, Integer> newMonomial = new Monomial<Number, Integer>(newCoefficient,
//							m.getDegree());
//					result.addMonomialToVector(newMonomial);
//					containsDegree = true;
//					continue;
//				}
//			} else {
//				result.addMonomialToVector(m);
//			}
//
//		}
//		if (!containsDegree) {
//			result.addMonomialToVector(monomial);
//		}
//		return result;
//	}
	
	
	
	public Polynomial add(Polynomial p1, Polynomial p2){
		Polynomial result = new Polynomial();
		for (Monomial<Number, Integer> m : p1.getPolynom()){
			result.add(m);
			
		}
		System.out.println(p1.toString());
		for (Monomial<Number, Integer> m : p2.getPolynom()){
			result.add(m);
		}
		System.out.println(p1.toString());
		return result;
	}
	
//	public Polynomial add(Polynomial p1, Polynomial p2){
//		Polynomial result = new Polynomial();
//		p1.LexicographicalOrder();
//		p2.LexicographicalOrder();
//		Vector<Monomial<Number, Integer>> p1Vector = p1.getPolynom();
//		Vector<Monomial<Number, Integer>> p2Vector = p2.getPolynom();
//		int sizeOfP1 = p1.getPolynom().size();
//		int sizeOfP2 = p2.getPolynom().size();
//		
//		int i = 0, j = 0;
//		while (i < sizeOfP1 && j < sizeOfP2){
//			if (p1Vector.get(i).compareTo(p2Vector.get(j)) < 0){
//				
//				result.addMonomialToVector(p1Vector.get(i));
//				i++;
//			} else if (p1Vector.get(i).compareTo(p2Vector.get(j)) == 0){
//				i++; j++;
//				Number newCoefficient = p1Vector.get(i).getCoefficient().doubleValue() + p2Vector.get(j).getCoefficient().doubleValue();
//				if (newCoefficient.doubleValue() == 0) {
//					continue;
//				} else {
//					Monomial<Number, Integer> newMonomial = new Monomial<Number, Integer>(newCoefficient, p1Vector.get(i).getDegree());
//					result.addMonomialToVector(newMonomial);
//					continue;
//				}
//			} else {
//				result.addMonomialToVector(p2Vector.get(j));
//				j++;
//			}
//			if (i == sizeOfP1){
//				while (j < sizeOfP2){
//					result.addMonomialToVector(p2Vector.get(j));
//					j++;
//				}
//			} else {
//				while (i < sizeOfP1){
//					result.addMonomialToVector(p1Vector.get(i));
//					i++;
//				}
//			}
//		}
//		return result;
//	}
	
	public Polynomial firstDerivative(Polynomial polynomial){
		Polynomial result = new Polynomial();
		for (Monomial<Number, Integer> m : polynomial.getPolynom()){
			result.addMonomialToVector(new Monomial<Number, Integer>(m.getCoefficient().doubleValue()*m.getDegree(), m.getDegree() - 1));
		}
		return result;
	}
	
	public double evaluate(Polynomial polynomial, double x){
		double result = 0;
		for (Monomial<Number, Integer> m : polynomial.getPolynom()){
			result += m.getCoefficient().doubleValue()*Math.pow(x, m.getDegree());
		}
		return result;
	}
	
	public Polynomial subtract(Polynomial p1, Polynomial p2){
		p2 = multiplyByConstant(p2, -1);
		Polynomial result = add(p1, p2);
		return result;
	}
}
