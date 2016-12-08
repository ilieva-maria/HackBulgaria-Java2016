package friday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> students = new ArrayList<>();
		students.add(new Student("Ivan", "Petrov", 6));
		students.add(new Student("Peter", "Ivanov", 5));
		students.add(new Student("Jenn", "Petrova", 6));
		students.add(new Student("Alice", "Ivanova", 3));
		// Collections.sort(students);

		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.getGrade(), o2.getGrade());
			}

		});
		System.out.println(Arrays.toString(students.toArray()));
	}

}
