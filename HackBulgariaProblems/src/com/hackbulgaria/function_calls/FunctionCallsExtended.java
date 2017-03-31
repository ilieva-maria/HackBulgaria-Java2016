package com.hackbulgaria.function_calls;

import java.util.HashMap;
import java.util.Scanner;

public class FunctionCallsExtended {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String[] functions = new String[n];
		scanner.nextLine();
		for (int i = 0; i < n; i++) {
			functions[i] = scanner.nextLine();
			parseLine(functions[i]);
		}
		String composition = scanner.nextLine();
		int x = scanner.nextInt();

		System.out.println(evaluate(composition, x));
		scanner.close();

	}

	private static HashMap<String, String> functionsMap = new HashMap<>();

	public static void parseLine(String line) {
		if (line.isEmpty()) {
			return;
		}
		String[] parts = line.split("=");
		String nameAndArgument = parts[0].trim();
		String functionBody = parts[1].trim();
		String functionName = getName(nameAndArgument);

		functionsMap.put(functionName, functionBody);
	}

	public static int evaluateFunction(String functionBody, int x) {
		if (functionBody.isEmpty()) {
			System.out.println("Empty line!");
			return 0;
		}
		String bodyParts[] = functionBody.split("\\s+");

		if (bodyParts.length == 1) {
			if (isInteger(bodyParts[0])) {
				return Integer.parseInt(bodyParts[0]);
			} else if (isFunction(bodyParts[0])) {
				String function = getFunctionName(bodyParts[0]);
				String argument = getFunctionArgument(bodyParts[0]);

				if (functionsMap.containsKey(function)) {
					if (isInteger(argument)) {
						return evaluateFunction(functionsMap.get(function), Integer.parseInt(argument));
					} else {
						return evaluateFunction(functionsMap.get(function), x);
					}

				} else {
					System.out.println("Function is not defined!");
					return 0;
				}
			} else {
				return x;
			}

		} else {
			char sign = indexOfFirstSign(functionBody);
			if (sign == '-') {
				String[] parts = functionBody.split("\\-", 2);
				return evaluateFunction(parts[0].trim(), x) - evaluateFunction(parts[1].trim(), x);
			} else if (sign == '+') {
				String[] parts = functionBody.split("\\+", 2);
				return evaluateFunction(parts[0].trim(), x) + evaluateFunction(parts[1].trim(), x);
			} else {
				System.out.println("There are no arithmetic opperations!");
			}
		}
		return 0;
	}

	public static char indexOfFirstSign(String functionBody) {
		int indexOfMinus = -1;
		int indexOfPlus = -1;

		if (functionBody.contains("-")) {
			indexOfMinus = functionBody.indexOf("-");
		}
		if (functionBody.contains("+")) {
			indexOfPlus = functionBody.indexOf("+");
		}
		if (indexOfMinus > 0 && (indexOfMinus < indexOfPlus || indexOfPlus == -1)) {
			return '-';
		} else if (indexOfPlus > 0 && (indexOfPlus < indexOfMinus || indexOfMinus == -1)) {
			return '+';
		} else {
			System.out.println("The function does not contain +/- sign!");
			return 0;
		}
	}

	public static int evaluate(String formula, int x) {
		String[] functions = formula.replaceAll("\\s+", "").split("\\.");
		for (int i = functions.length - 1; i >= 0; i--) {
			x = evaluateFunction(functionsMap.get(functions[i]), x);
		}
		return x;
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isFunction(String string){
		return string.startsWith("(") && string.endsWith(")");
	}
	
	public static String getName(String nameAndArgument){
		return nameAndArgument.split("\\s+", 2)[0];
	}
	
	public static String[] getArgument(String nameAndArgument){
		return nameAndArgument.split("\\s+", 2)[1].split("\\s+");
	}
	
	public static String getFunctionName(String function){
		return function.substring(0, function.indexOf("(")).trim();
	}
	
	public static String getFunctionArgument(String function) {
		return function.substring(function.indexOf("(")+1, function.indexOf(")")).replaceAll("\\s+", "");
	}

}