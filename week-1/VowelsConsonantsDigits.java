public class VowelsConsonantsDigits{
	public static void main(String[] args){
		String[] vowsConsDigits = vowelsConsonantsDigits("It is 18th of Oct 2016");
		for (String str : vowsConsDigits){
			System.out.println(str);
		}
	}
	public static String [] vowelsConsonantsDigits(String sentence){
// 		Example: vowelsConsonantsDigits("It is 18th of Oct 2016")

//      Result: ["Vowels: 4", "Consonants: 7", "Digits: 6"]
		String [] result = new String[3];
		result[0] = "Vowels: " + Integer.toString(vowelsCount(sentence));
		result[1] = "Consonants: " + Integer.toString(consonantsCount(sentence));
		result[2] = "Digits: " + Integer.toString(digitsCount(sentence));
		return result;
	}

	public static int digitsCount(String sentence){
		return sentence.replaceAll("[^0-9]+", "").length();
	}

	public static int vowelsCount(String sentence){
		return sentence.replaceAll("[^aeiouyAEIOUY]+", "").length();
	}

	public static int consonantsCount(String sentence){
		return sentence.replaceAll("[^a-zA-Z]+", "").length() - vowelsCount(sentence);
	}
}