public class IsOdd {
		public static void main(String[] args){
			System.out.println(isNumberOdd(5));
			System.out.println(isNumberOdd(2));
		}
  		public static boolean isNumberOdd(int number){
  			return (number%2 != 0);
  		}

}