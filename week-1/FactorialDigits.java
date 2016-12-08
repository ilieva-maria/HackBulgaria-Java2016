public class FactorialDigits{
	public static void main(String[] args) {
		System.out.println(factorialDigits(5));
		System.out.println(factorialDigits(145));
	}
	
	public static int factorial(int number){
		int fact = 1;
		for (int i = 2; i <= number; i++){
			fact *= i;
		}
		return fact;
	}

	public static int factorialDigits(int number){
		int sum = 0;
		while (number > 0){
			sum += factorial(number%10);
			number = number/10;
		}
		return sum;
	}
}
