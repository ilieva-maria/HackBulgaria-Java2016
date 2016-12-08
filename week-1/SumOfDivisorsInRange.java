public class SumOfDivisorsInRange{
	public static void main(String[] args){
		System.out.println(sumOfDivisors(4, 7));
	}
	public static int sumOfDivisors(int numberA, int numberB){
		int sumOfDivisors = 0;
		for (int i = numberA; i <= numberB; i++){
			sumOfDivisors += sumOfDivisorsOfNumber(i);
			System.out.println(i + " " + sumOfDivisorsOfNumber(i));
		}
		return sumOfDivisors;
	}

	public static int sumOfDivisorsOfNumber(int number){
		if (number == 0){
			return 0;
		} else if (number < 0){
			return sumOfDivisorsOfNumber(-number);
		} else {
			int sumOfDivisors = number + 1;
			for (int i = 2; i < Math.sqrt(number) + 1; i++){
				if (number % i == 0){
					sumOfDivisors += i;
				}
			}
			return sumOfDivisors;
		}
		
	}
}