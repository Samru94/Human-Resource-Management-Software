package de.progex.main;

import java.sql.Date;

import de.progex.database.Database;
import de.progex.model.Employee;
import de.progex.model.Leave;

public class TestMain {
	// creating three employee objects
	static Employee emp1 = new Employee("Max", "Muster", "m@abc.com", " a-str 14", "m city", "c++, java", "d-1",
			2341.45);
	static Employee emp2 = new Employee("Sen", "Name", "n@abc.com", "frankfurt str 15", "berlin city", "python, java",
			"d-1", 4541.45);
	static Employee emp3 = new Employee("Name", "OhneName", "Name1@abc.com", "berlin str 112", "berlin city", "network",
			"d-5", 1241.45);

	// create the database object to access its functions
	static Database db = new Database();

	public static void main(String[] args) throws IllegalAccessException {
		// When program launches, these two functions should be called!
		db.start_employeeTable();
		db.start_leaveTable();

		/**** Insert function here ****/
		 test1();
		System.out.println(emp1.isVerified());
		/******************************/

		System.out.println("\n\n\n\n");

		// when the program closes, this function should be called.
		// ignore the error message
		db.closeDB();
	}

	// create three employees and print them all
	public static void test1() {
		db.createEmployee(emp1);
		db.createEmployee(emp2);
		db.createEmployee(emp3);

		Employee[] employeeArr = db.getAllEmployee();

		for (int i = 0; i < employeeArr.length; i++) {
			if (employeeArr[i] != null) {
				Employee tmp = employeeArr[i];
				System.out.println(tmp.getEmployee_id() + "...." + tmp.getSalary());
				System.out.println(tmp.getFirstName() + "...." + tmp.getEmail());
				System.out.println("\n");
				tmp = null;
			}
		}
	}

	// get all employees, delete one employee and then print the data again
	public static void test2() {
		db.createEmployee(emp1);
		db.createEmployee(emp2);
		db.createEmployee(emp3);

		Employee[] employeeArr = db.getAllEmployee();
		for (int i = 0; i < employeeArr.length; i++) {
			if (employeeArr[i] != null) {
				Employee tmp = employeeArr[i];
				System.out.println(tmp.getEmployee_id() + "...." + tmp.getFirstName());
				tmp = null;
			}
		}

		db.deleteEmployee(2);

		employeeArr = db.getAllEmployee();
		for (int i = 0; i < employeeArr.length; i++) {
			if (employeeArr[i] != null) {
				Employee tmp = employeeArr[i];
				System.out.println(tmp.getEmployee_id() + "...." + tmp.getFirstName());
				tmp = null;
			}
		}
	}

	// assign multiple leaves to one employee and print the data
	public static void test3() {
		db.createEmployee(emp1); // DB ID 1
		db.createEmployee(emp2); // DB ID 2
		db.createEmployee(emp3); // DB ID 3

		Date sdt = Date.valueOf("2020-05-01"); // declare start date
		Date edt = Date.valueOf("2020-05-25"); // declare end date
		Leave le = new Leave("holiday", sdt, edt);

		db.createLeave(2, le); // Create holiday for employee with the ID 2

		sdt = Date.valueOf("2020-08-01"); // declare start date
		edt = Date.valueOf("2020-09-25"); // declare end date
		le = new Leave("business", sdt, edt);

		db.createLeave(2, le); // Create holiday for employee with the ID 2

		// get leave from database for employee with the id 2
		Leave[] leaveArr = db.getLeave(2);
		// get the employee from database with the id 2
		Employee employee = db.getEmployeeById(2);
		System.out.println("Name........" + employee.getFirstName());
		for (int i = 0; i < leaveArr.length; i++) {
			if (leaveArr[i] != null) {
				Leave ltmp = leaveArr[i];
				if (ltmp.getStartDate() != null) {
					System.out.println("Status......" + ltmp.getStatus());
					System.out.println("From........" + ltmp.getStartDate() + " to " + ltmp.getEndDate());
				}
				ltmp = null;
			}
		}

	}

	// get leave data between certain dates
	public static void test4() {
		db.createEmployee(emp1);
		db.createEmployee(emp2);
		db.createEmployee(emp3);

		// create multiple dates to insert into database
		Date sdt = Date.valueOf("2020-05-01");
		Date edt = Date.valueOf("2020-05-25");
		Leave le = new Leave("holiday", sdt, edt);
		db.createLeave(2, le); // id 2 empoloyee goes on holiday

		sdt = Date.valueOf("2020-08-15");
		edt = Date.valueOf("2020-09-24");
		le = new Leave("business", sdt, edt);
		db.createLeave(3, le); // id 3 employee goes on business trip

		// get every date between these dates
		Date startingDate = Date.valueOf("2020-01-01");
		Date endingDate = Date.valueOf("2020-12-30");
		Leave[] leaveArr = db.getAllLeaveByDate(startingDate, endingDate);

		for (int i = 0; i < leaveArr.length; i++) {
			if (leaveArr[i] != null) {
				Leave ltmp = leaveArr[i];
				if (ltmp.getStartDate() != null) {
					System.out.println("From........" + ltmp.getStartDate() + " to " + ltmp.getEndDate());
				}
				ltmp = null;
			}
		}

	}

	// get employees by searching a word
	public static void test5() {
		db.createEmployee(emp1);
		db.createEmployee(emp2);
		db.createEmployee(emp3);

		// search for firstname, lastname, skills, city, or department
		Employee[] employeeArr = db.searchEmployee("java");

		for (int i = 0; i < employeeArr.length; i++) {
			if (employeeArr[i] != null) {
				Employee tmp = employeeArr[i];
				System.out.println(tmp.getEmployee_id() + "...." + tmp.getFirstName() + "...." + tmp.getLastName());
				tmp = null;
			}
		}
	}
}