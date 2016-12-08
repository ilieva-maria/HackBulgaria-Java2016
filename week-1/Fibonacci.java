public class Fibonacci{
	public static void main(String[] args) {
		System.out.println(fibonacci(0));
		System.out.println(fibonacci(1));
		System.out.println(fibonacci(5));
		System.out.println(fibonacciSequence(3));
	}
	public static int fibonacciSequence(int number){
		String fibonacciSeq = "";
		for (int i = 0; i < number; i++){
			fibonacciSeq += Integer.toString(fibonacci(i));
		}
		return Integer.parseInt(fibonacciSeq);

	}
	public static int fibonacci(int number){
		if (number == 0 || number == 1){
			return 1;
		} else {
			return fibonacci(number-1)+fibonacci(number-2);
		}
	}
}

