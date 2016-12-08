package polynomial;

import java.util.Collections;
import java.util.Vector;

public class Polynomial {
	private Vector<Monomial<Number,Integer>> polynom;
	
	public Polynomial() {
		polynom = new Vector<>();
	}
	
	public Vector<Monomial<Number, Integer>> getPolynom() {
		return polynom;
	}


	
	public void add(Monomial<Number, Integer> monomial) {
		for (Monomial<Number, Integer> m : polynom) {
			if (m.compareTo(monomial) == 0){
				Number newCoefficient = m.getCoefficient().doubleValue()+monomial.getCoefficient().doubleValue();
				if (newCoefficient.doubleValue() == 0){
					return;
				}else {
					m.setCoefficient(newCoefficient);
					return;
				}
			}
		}
		polynom.addElement(monomial);
	}
	
	public void addMonomialToVector(Monomial<Number, Integer> monomial){
		polynom.addElement(monomial);
	}

	
	
	public void LexicographicalOrder() {
		Collections.sort(polynom);
	}

	@Override
	public String toString() {
		StringBuilder polynomial = new StringBuilder();
		boolean isFirst = true;
		for (Monomial<Number, Integer> m : polynom){
			if (isFirst){
				polynomial.append(m.toString());
				isFirst = false;
			} else {
				polynomial.append(" + ").append(m.toString());
			}
			
			
		}
		return polynomial.toString();
	}

	
	
	
	
}
