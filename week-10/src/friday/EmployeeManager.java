package friday;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeManager {
	private Session session;

	public EmployeeManager(Session session) {
		this.session = session;
	}

	public Integer addEmployee(String fname, String lname, int salary) {
		Integer employeeId = null;

		Employee e = new Employee(fname, lname, salary);
		employeeId = (Integer) session.save(e);

		return employeeId;
	}

	public void listEmployees() {
		@SuppressWarnings("unchecked")
		List<Employee> employees = session.createQuery("FROM Employee").getResultList();
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			System.out.print("First Name: " + employee.getFirstName());
			System.out.print("  Last Name: " + employee.getLastName());
			System.out.println("  Salary: " + employee.getSalary());
		}

	}

	public void updateEmployee(Integer EmployeeID, int salary) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			employee.setSalary(salary);
			session.update(employee);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();

			}
		}

	}

	public void deleteEmployee(Integer EmployeeID) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
				e.printStackTrace();
			}
		}

	}

}
