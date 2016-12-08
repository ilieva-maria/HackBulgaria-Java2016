package friday;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SherlockAndArray {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfTestCases = scanner.nextInt();
		for (int i = 0; i < numberOfTestCases; i++) {
			int n = scanner.nextInt();
			List<Integer> list = new LinkedList<>();
			for (int j = 0; j < n; j++) {
				list.add(scanner.nextInt());
			}
			System.out.println(isSherlockArray(list) == true ? "YES" : "NO");
		}
		scanner.close();

	}

	public static boolean sherlockArray(List<Integer> list) {
		List<Integer> left = new LinkedList<>();
		int leftSum = 0;
		for (int i = 0; i < list.size(); i++) {
			leftSum += list.get(i);
			left.add(leftSum);
		}
		List<Integer> right = new LinkedList<>();
		int rightSum = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			rightSum += list.get(i);
			right.add(0, rightSum);
		}
		for (int i = 0; i < list.size(); i++) {
			if (left.get(i) == right.get(i)) {

				return true;
			}

		}
		return false;
	}

	public static boolean isSherlockArray(List<Integer> list) {
		int rightSum = list.stream().mapToInt(Integer::intValue).sum();
		int leftSum = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			if (leftSum == rightSum - list.get(i) - leftSum) {
				return true;
			}
			leftSum += list.get(i);
		}
		return false;
	}
}
