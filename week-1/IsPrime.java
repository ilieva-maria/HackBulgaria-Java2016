public class IsPrime{
	public static void main(String[] args) {
		System.out.println(isNumberPrime(8));
	}
	public static boolean isNumberPrime(int number){
		if (number < 2){
			return false;
		} else if (number == 2){
			return true;
		} else {
			for (int i = 2; i < Math.sqrt(number); i++){
				if (number%i == 0){
					return false;
				}
			}
			return true;
		}
	}
}