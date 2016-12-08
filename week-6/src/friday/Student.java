package friday;

public class Student implements Comparable<Student> {
	private final String firstName;
	private final String lastName;
	private int grade;

	public Student(String firstName, String lastName, int grade) {
		this.firstName = firstName;
		this.lastName = lastName;
		setGrade(grade);
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public int compareTo(Student o) {
		return this.getFirstName().compareTo(o.getFirstName());
	}

	@Override
	public String toString() {
		return "[" + getFirstName() + " ," + getLastName() + " ," + getGrade() + "]";
	}

	public String getLastName() {
		return lastName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
