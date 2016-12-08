package friday;


public class BinarySearch {

	// 1 2 5 8 11 15 20 25
	public static void main(String[] args) {
		int[] arr = { 1, 2, 5, 8, 11, 15, 20, 25 };

		// System.out.println(search(arr, 2));
		// System.out.println(search(arr, 25));
		// System.out.println(search(arr, 21555));
		// System.out.println(search(arr, 6));
		// System.out.println(search(arr, 8));
		System.out.println(upperBound(arr, 26));
		System.out.println(lowerBound(arr, 0));
		System.out.println(interpolationSearch(arr, 25));

	}

	public static int search(int[] arr, int target) {
		int low = 0;
		int high = arr.length - 1;
		int indexOfTarget = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			
			if (arr[mid] == target) {
				indexOfTarget = mid;
				break;
			} else if (arr[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return indexOfTarget;
	}


	public static int interpolationSearch(int[] arr, int target) {
		int low = 0;
		int high = arr.length - 1;
		int indexOfTarget = -1;

		while (low <= high) {
			
			int mid = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);
			if (mid < 0 || mid >= arr.length) {
				break;
			}
			if (arr[mid] == target) {
				indexOfTarget = mid;
				break;
			} else if (arr[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		
		return indexOfTarget;
	}

	public static int upperBound(int[] arr, int target) {
		int low = 0;
		int high = arr.length - 1;
		int indexOfTarget = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == target) {
				indexOfTarget = mid;
				break;
			} else if (arr[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		int index = indexOfTarget == -1 ? low : indexOfTarget;
		if (index > arr.length - 1) {
			// throw new IndexOutOfBoundsException("There is no bigger element
			// in the array.");
			System.out.println("There is no bigger element in the array! The last element is: " + arr[arr.length - 1]);
			index = arr.length - 1;
		}
		int element = arr[index];
		System.out.print("element: " + element + " index: ");
		return index;
	}

	public static int lowerBound(int[] arr, int target) {
		int low = 0;
		int high = arr.length - 1;
		int indexOfTarget = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == target) {
				indexOfTarget = mid;
				break;
			} else if (arr[mid] > target) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		int index = indexOfTarget == -1 ? high : indexOfTarget;
		if (index < 0) {
			// throw new IndexOutOfBoundsException("There is no bigger element
			// in the array.");
			System.out.println("There is no smaller element in the array! The first element is: " + arr[0]);
			index = 0;
		}
		int element = arr[index];
		System.out.print("element: " + element + " index: ");
		return index;
	}
}
