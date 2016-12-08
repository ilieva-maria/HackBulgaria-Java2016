package com.parsing;
import com.listify.Listify;

public class Parser{
	public static void main(String[] args) {
		parse(Listify.readToArray("com/parsing/panda.ini"));	
	}

	public static void parse(String[] lines){
		System.out.println("{");
		boolean flag = true;
		boolean lastWasPair = false;
		for (String line : lines){
			if (line.length() != 0){
				line.replaceAll("\\s+", "");
				if (line.charAt(0) == '['){
					if (!flag){
						System.out.println("  },");
					}
					System.out.println("\"" + line.substring(1, line.length()-1) + "\": {");
					flag = false;
				} else {
					String[] pair = line.split("=|;");

					System.out.println("    \"" + pair[0].trim() + "\": " + "\"" + pair[1] + "\",");
				}
			}	
		}
		System.out.println("  }");
		System.out.println("}");
		

	}
}
