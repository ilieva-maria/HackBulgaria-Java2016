package vector;

public class Main {
	public static void main(String[] args) {

		// int[] arr = new int[10];

		Vector<Integer> v = new Vector<Integer>();
		// IntVector v2 = new IntVector(arr);
		// IntVector v3 = new IntVector(1000);

		Vector<String> vec = new Vector<>();

		System.out.println(vec.toString());

		for (int i = 0; i < 100; i++) {
			v.add(i);
		}

		for (int i = 0; i < 20; i++) {
			vec.add("hello" + i);
		}

		System.out.println(vec.toString());

		System.out.println(v);
		System.out.println(v.size());
	}
}
