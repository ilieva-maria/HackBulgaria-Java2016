package functionCalls;

import java.util.Scanner;

public class FunctionCalls {
	public static int evaluate(String formula, int x) {
		String[] functions = formula.replaceAll("\\s+", "").split("\\.");

		for (int i = functions.length - 1; i >= 0; i--) {

			switch (functions[i]) {
			case "f1":
				x = f1(x);
				break;

			case "f2":
				x = f2(x);
				break;

			case "f3":
				x = f3(x);
				break;

			case "f4":
				x = f4(x);
				break;

			default:
				System.out.println("We do not have such a function!");
			}
		}
		return x;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		int x = scanner.nextInt();

		System.out.println(evaluate(input, x));
		scanner.close();

	}

	public static int f1(int x) {
		return f2(x) + f3(x);
	}

	public static int f2(int x) {
		return 2 * x;
	}

	public static int f3(int x) {
		return x + 1;
	}

	public static int f4(int x) {
		return 2 * f1(x);
	}

}