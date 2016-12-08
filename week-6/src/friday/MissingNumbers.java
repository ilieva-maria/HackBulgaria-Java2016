package friday;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MissingNumbers {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		List<Integer> listA = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			listA.add(scanner.nextInt());
		}

		int m = scanner.nextInt();
		List<Integer> listB = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			listB.add(scanner.nextInt());
		}

		System.out.println(Arrays.toString(missingNumbers(listA, listB).toArray()));
		scanner.close();
	}

	public static List<Integer> missingNumbers(List<Integer> listA, List<Integer> listB) {
		int max = Collections.max(listB);
		int min = Collections.min(listB);
		int k = max - min + 1;

		int[] arrA = makeHistogram(listA, min, k);
		int[] arrB = makeHistogram(listB, min, k);

		List<Integer> missingNumbers = new LinkedList<>();
		for (int i = 0; i < arrB.length; i++) {
			if (arrA[i] < arrB[i]) {
				missingNumbers.add(i + min);
			}
		}
		return missingNumbers;
	}

	public static int[] makeHistogram(List<Integer> list, int min, int k) {
		int[] arr = new int[k];
		for (int i = 0; i < list.size(); i++) {
			arr[list.get(i) - min] += 1;
		}
		return arr;
	}
}
