package tuesday;

import java.io.FileNotFoundException;

public class ExceptionsExample {
	public static void main(String[] args) {

	}

	public void printFile(String pathToFile) throws FileNotFoundException {
		throw new FileNotFoundException("The file is not present.");
	}
}
