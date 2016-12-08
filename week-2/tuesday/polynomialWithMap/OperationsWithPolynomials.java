package polynomialWithMap;

import java.util.Iterator;
import java.util.Map;

public class OperationsWithPolynomials {
	public Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial();
		Iterator<Map.Entry<Integer, Integer>> it1 = p1.getPolynom().entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry<Integer, Integer> pair1 = it1.next();
			Iterator<Map.Entry<Integer, Integer>> it2 = p2.getPolynom().entrySet().iterator();
			while (it2.hasNext()) {
				Map.Entry<Integer, Integer> pair2 = it2.next();
				result.add(pair1.getValue() * pair2.getValue(), pair1.getKey() + pair2.getKey());
			}
		}
		return result;
	}

	public Polynomial multiplyByConstant(Polynomial polynomial, int constant) {
		Polynomial result = new Polynomial();
		Iterator<Map.Entry<Integer, Integer>> it = polynomial.getPolynom().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			result.add((int) pair.getValue() * constant, (int) pair.getKey());
		}
		return result;
	}

	public Polynomial firstDerivative(Polynomial polynomial) {
		Polynomial result = new Polynomial();
		Iterator<Map.Entry<Integer, Integer>> it = polynomial.getPolynom().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			if (pair.getKey() == 0) {
				continue;
			}
			result.add(pair.getValue() * pair.getKey(), pair.getKey() - 1);
		}
		return result;
	}

	public int evaluate(Polynomial polynomial, int x) {
		int result = 0;
		Iterator<Map.Entry<Integer, Integer>> it = polynomial.getPolynom().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			result += pair.getValue() * Math.pow(x, pair.getKey());
		}
		return result;
	}

	public Polynomial add(Polynomial p1, Polynomial p2) {
		Polynomial result = new Polynomial();

		Iterator<Map.Entry<Integer, Integer>> it1 = p1.getPolynom().entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry<Integer, Integer> pair = it1.next();
			result.add(pair.getValue(), pair.getKey());
		}
		Iterator<Map.Entry<Integer, Integer>> it2 = p2.getPolynom().entrySet().iterator();
		while (it2.hasNext()) {
			Map.Entry<Integer, Integer> pair = it2.next();
			result.add(pair.getValue(), pair.getKey());
		}
		return result;
	}

	public Polynomial subtract(Polynomial p1, Polynomial p2) {
		p2 = multiplyByConstant(p2, -1);
		Polynomial result = add(p1, p2);
		return result;
	}

}
