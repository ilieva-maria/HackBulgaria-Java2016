package tuesday;

import java.util.HashMap;
import java.util.Scanner;

public class TypeChecking {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String line = scanner.nextLine();
		while (!line.isEmpty()) {
			parseLine(line);
			line = scanner.nextLine();
		}
		String composition = scanner.nextLine();

		System.out.println(isValid(composition));

		scanner.close();
	}

	private static HashMap<String, String> functionsMap = new HashMap<>();

	public static void parseLine(String line) {
		String[] parts = line.split("::");

		String functionName = parts[0].trim();
		String functionDefinition = parts[1].trim();

		functionsMap.put(functionName, functionDefinition);
	}

	public static boolean isValid(String composition) {
		boolean isValid = true;
		String[] functions = composition.replaceAll("\\s+", "").split("\\.");
		for (int i = functions.length - 1; i > 0; i--) {
			System.out.println(functions[i] + " " + functions[i - 1]);
			isValid = isValid && isValidComposition(functionsMap.get(functions[i]), functionsMap.get(functions[i - 1]));
		}
		return isValid;
	}

	public static boolean isValidComposition(String first, String second) {
		String[] typesOfFirstFunction = first.split("->");
		String firstReturns = typesOfFirstFunction[1].trim();
		String[] typesOfSecondFunction = second.split("->");
		String secondTakes = typesOfSecondFunction[0].trim();
		System.out.println(firstReturns + " " + secondTakes);
		if (!firstReturns.equals(secondTakes)) {
			System.out.println(firstReturns + " " + secondTakes);
			return false;
		}
		return true;
	}

}
