package parser;

import listify.Listify;

public class Parser{
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("File not provided!");
			return;
		}

		String[] content = Listify.readToArray(args[0]);
		
		convertIniToJson(content);
	}

	public static void convertIniToJson(String[] iniContent){
		boolean inObject = false, lastWasPair = false;

		System.out.println("{");

		for (String line : iniContent){
			line = trimComment(line);
			if (isObject(line)){
				if (inObject){
					printObjectEnd();
				}
				lastWasPair = false;
				inObject = true;
				printObject(getObject(line.trim()));
			} else if (isKeyVal(line)) {
				if (inObject){
					if(lastWasPair) {
						System.out.println(",");
					}
					String[] kvPair = getKeyValPair(line);
					printKeyValPair(kvPair[0].trim(), kvPair[1].trim());
					lastWasPair = true;
				}
			} else if (isEmpty(line)){
				if (inObject) {
					printObjectEnd();
				}
				inObject = false;
				lastWasPair = false;
			} else {
				continue;
			}
			
		}
		System.out.println("}");
	}



	public static boolean isObject(String line) {
		return line.startsWith("[") && line.endsWith("]");
	}

	public static String getObject(String line) {
		return line.substring(1, line.length() - 1).trim();
	}

	public static boolean isKeyVal(String line) {
		return line.contains("=");
	}

	public static String[] getKeyValPair(String line) {
		int equalsSign = line.indexOf("=");
		String[] pair = line.split("=", equalsSign + 1);

		return pair;
	}

	public static boolean isEmpty(String line) {
		return line.trim().isEmpty();
	}

	public static String trimComment(String line) {
		if (line.indexOf(";") > 0){
			return line.substring(0, line.indexOf(";"));
		} 
		return line;
	}

	public static void printObject(String name) {
		System.out.println("  \""+name+"\": {");
	}

	public static void printKeyValPair(String key, String value) {
		System.out.print("    \""+key+"\": \""+value+"\"");	
	}

	public static void printObjectEnd() {
		System.out.println();
		System.out.println("  },");
	}
}