import java.lang.StringBuilder;
public class ReverseOddWords{
	public static void main(String[] args) {
		System.out.println(reverseWord("This"));
		System.out.println(reverseOddWords("This is the first lecture for Programming 101 with Java"));
	}
	public static boolean isNumberOdd(int number){
  			return (number%2 != 0);
  		}

	public static String reverseOddWords(String sentence){
		StringBuilder reversedSentence = new StringBuilder();
		String[] words = sentence.split(" ");

		for (int i = 0; i < words.length; i++){
			if(isNumberOdd(i)){
				reversedSentence.append(reverseWord(words[i])).append(" ");
			}else {
				reversedSentence.append(words[i]).append(" ");
			}
		}
		return reversedSentence.toString();
	}
	public static String reverseWord(String word){
    	if (word.length() <= 1) { 
        	return word;
    	} else {
    		return reverseWord(word.substring(1)) + word.charAt(0);
    	}
	}
}