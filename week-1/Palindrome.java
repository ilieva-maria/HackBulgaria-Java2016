public class Palindrome{
	public static void main(String[] args) {
		System.out.println(reverseNumber(123));
		System.out.println(isPalindrome(12340321));
		System.out.println(isPalindrome(121));

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