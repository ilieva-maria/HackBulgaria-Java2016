package com.hackbulgaria.polynomial;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PolynomialTest {
	
	private final Polynomial p1 = UserInput.fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8");
	private final Polynomial p2 = UserInput.fromString("2x^4 + 3x^2 - 10x + 3 + 9x^8 + 7x^4 + 15x^3");
	
	@Test
	public void testFromString() {
		assertEquals("9x^8 + 2x^4 + 3x^2 - 10x + 3", p1.toString());
		assertEquals("9x^8 + 9x^4 + 15x^3 + 3x^2 - 10x + 3", p2.toString());
	}
	
	@Test
	public void testMultiplyByConstant() {
		Polynomial p = OperationsWithPolynomials.multiplyByConstant(p1, 2);
		assertEquals("18x^8 + 4x^4 + 6x^2 - 20x + 6", p.toString());
	}
	
	@Test
	public void testAdd() {
		Polynomial p = OperationsWithPolynomials.add(p1, p2);
		assertEquals("18x^8 + 11x^4 + 15x^3 + 6x^2 - 20x + 6", p.toString());
	}
	
	@Test
	public void testSubtract() {
		Polynomial p = OperationsWithPolynomials.subtract(p1, p2);
		assertEquals("-7x^4 - 15x^3", p.toString());
	}
	
	@Test
	public void testEvaluate() {
		long actualResult = OperationsWithPolynomials.evaluate(p1, 2);
		assertEquals(2331, actualResult);
	}
	
	@Test
	public void testFirstDerivative() {
		String expected = "8x^3 + 6x - 10";
		Polynomial p = UserInput.fromString("2x^4 + 3x^2 - 10x + 3");
		String actual = OperationsWithPolynomials.firstDerivative(p).toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMultiply() {
		Polynomial p1 = UserInput.fromString("2x - 1");
		Polynomial p2 = UserInput.fromString("3x + 4");
		
		String actual = OperationsWithPolynomials.multiply(p1, p2).toString();
		String expected = "6x^2 + 5x - 4";
		assertEquals(expected, actual);
	}
	
}
