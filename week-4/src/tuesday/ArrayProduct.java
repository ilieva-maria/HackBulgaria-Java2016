package tuesday;

public class ArrayProduct {
	public static void main(String[] args) {
		int[] a = { 1, 5, 8, 10, 0, 5, 0, 99999 };
		System.out.println(productWithoutElement(a, 0));
		System.out.println(productWithoutElement(a, 3));
		System.out.println(productWithoutElement(a, 4));
	}

	public static int productWithoutElement(int[] array, int index) {
		if (index < 0 || index >= array.length) {
			throw new IndexOutOfBoundsException();
		} else {
			int product = 1;
			for (int i = 0; i < array.length; i++) {
				if (index != i) {
					product *= array[i];
				}

			}
			return product;
		}

	}

}
