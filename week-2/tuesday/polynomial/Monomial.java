package polynomial;

public class Monomial<C extends Number, D extends Number> implements Comparable<Object>{
	private C coefficient;
	private D degree;

	public Monomial(C coefficient, D degree) {
		this.coefficient = coefficient;
		this.degree = degree;
	}

	public C getCoefficient() {
		return coefficient;
	}

	public D getDegree() {
		return degree;
	}

	public void setCoefficient(C coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public String toString() {
		String variable = (degree.doubleValue() > 1 ? "x^" : "");
		if (degree.doubleValue() == 1) {
			return formatDouble(coefficient.doubleValue()) + "x";
		}
		return formatDouble(coefficient.doubleValue()) + variable + formatDouble(degree.doubleValue());
	}
	
	public static String formatDouble(double d)
	{
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%s",d);
	}

	@Override
	public int compareTo(Object o) {
		@SuppressWarnings("unchecked")
		Monomial<Number, Integer> m = (Monomial<Number, Integer>) o;
		return Double.compare(m.getDegree().doubleValue(), degree.doubleValue());

	}

}
