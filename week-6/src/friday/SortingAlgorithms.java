package friday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingAlgorithms<T extends Comparable<T>> {
	public void swap(List<T> list, int i, int j) {
		Collections.swap(list, i, j);
	}

	public void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public void selectionSort(List<T> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			int min = i;
			for (int j = i; j < list.size(); j++) {
				if (list.get(j).compareTo(list.get(min)) < 0) {
					min = j;
				}
			}
			swap(list, min, i);
		}
	}

	public void insertionSort(List<T> list) {
		for (int i = 1; i < list.size(); i++) {
			boolean swapped = true;
			for (int j = i - 1; j > 0 && swapped; j--) {
				if (list.get(j).compareTo(list.get(j - 1)) <= 0) {
					swap(list, j, j - 1);
				} else {
					swapped = false;
				}
			}
		}
	}

	public void bubbleSort(List<T> list) {
		boolean swapped = true;
		int j = -1;
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 1; i < list.size() - j; i++) {
				if (list.get(i - 1).compareTo(list.get(i)) > 0) {
					swap(list, i, i - 1);
					swapped = true;
				}
			}
		}

	}

	public void countingSort(List<Integer> list) {
		int max = Collections.max(list);
		int min = Collections.min(list);
		int k = max - min + 1;

		int[] arr = new int[k];
		for (int i = 0; i < list.size(); i++) {
			arr[list.get(i) - min] += 1;
		}
		list.clear();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i]; j++) {
				list.add(i + min);
			}
		}
	}

	public void quickSort(int[] arr, int left, int right) {
		if (right - left < 2) {
			return;
		}
		int index = partition(arr, left, right);
		quickSort(arr, left, index - 1);
		quickSort(arr, index, right);
	}

	public int partition(int[] arr, int left, int right) {
		int i = left;
		int j = right;
		int pivot = arr[(left + (right - left) / 2)];
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if (i <= j) {
				swap(arr, i, j);
				i++;
				j--;
			}
		}
		return i;
	}

	public void merge(int[] left, int[] right, int[] arr) {
		int leftSize = left.length;
		int rightSize = right.length;
		int i = 0, j = 0, k = -1;
		while (i < leftSize && j < rightSize) {
			if (left[i] < right[j]) {
				arr[++k] = left[i++];
			} else {
				arr[++k] = right[j++];
			}
		}
		while (i < leftSize) {
			arr[++k] = left[i++];
		}
		while (j < rightSize) {
			arr[++k] = right[j++];
		}
	}

	public void mergeSort(int[] arr) {
		int arrSize = arr.length;
		if (arrSize < 2) {
			return;
		}
		int mid = arrSize / 2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, arrSize);
		mergeSort(left);
		mergeSort(right);
		merge(left, right, arr);
	}

	public static void main(String[] args) {
		SortingAlgorithms<Integer> s = new SortingAlgorithms<>();
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 8, 2, -1, -1, 13, 11, 7));
		int[] arr = new int[] { 1, 5, 8, 2, -1, -1, 13, 11, 7 };
		// s.selectionSort(list);
		// System.out.println(Arrays.toString(list.toArray()));
		// s.insertionSort(list);
		// System.out.println(Arrays.toString(list.toArray()));
		// s.bubbleSort(list);
		// System.out.println(Arrays.toString(list.toArray()));
		s.countingSort(list);
		System.out.println(Arrays.toString(list.toArray()));
		// s.quickSort(arr, 0, arr.length-1);
		// System.out.println(Arrays.toString(arr));
		s.mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
