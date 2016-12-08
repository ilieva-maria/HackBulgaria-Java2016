public class IsSumPalindrome{
	public static void main(String[] args) {
		System.out.println(isSumPalindrome(12289));
	}
	public static boolean isSumPalindrome(int input){
		System.out.println(input + reverseNumber(input));
		return isPalindrome(input + reverseNumber(input));
  	}

	public static boolean isPalindrome(int number){
		return (number == reverseNumber(number));
	}

	public static int reverseNumber(int number){
		if (number < 0){
			number = -number;
		}
		int reversedNumber = 0;
		while (number > 0){
			reversedNumber *= 10;
			reversedNumber += number%10;
			number = number/10;
		}
		return reversedNumber;
	}
}