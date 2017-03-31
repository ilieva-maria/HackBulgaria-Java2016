package com.hackbulgaria.polynomial;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Polynomial {
	private Map<Integer, Integer> polynom;

	public Polynomial() {
		polynom = new TreeMap<Integer, Integer>(Collections.reverseOrder());
	}

	public Map<Integer, Integer> getPolynom() {
		return polynom;
	}

	public void add(int coefficient, int degree) {

		Iterator<Map.Entry<Integer, Integer>> it = polynom.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			if (pair.getKey().equals(degree)) {
				int newCoefficient = pair.getValue() + coefficient;
				if (newCoefficient == 0) {
					polynom.remove(degree);
					return;
				} else {
					polynom.put(degree, newCoefficient);
					return;
				}
			}
		}
		polynom.put(degree, coefficient);
	}

	@Override
	public String toString() {
		StringBuilder polynomial = new StringBuilder();
		boolean isFirst = true;
		Iterator<Entry<Integer, Integer>> it = polynom.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Integer> pair = it.next();
			String pairStr = pairToString(pair);
			if (isFirst) {
				polynomial.append(pairStr);
				isFirst = false;
			} else {
				if (pairStr.startsWith("-")) {
					polynomial.append(" - ").append(pairStr.substring(1));
				} else {
					polynomial.append(" + ").append(pairStr);
				}

			}
		}

		return polynomial.toString();
	}

	public String pairToString(Map.Entry<Integer, Integer> pair) {
		String variable = (pair.getKey() > 1 ? "x^" : "");
		if (pair.getKey() == 0) {
			return pair.getValue().toString();
		}
		if (pair.getKey() == 1) {
			return pair.getValue() + "x";
		}
		return pair.getValue().toString() + variable + pair.getKey().toString();
	}

}
