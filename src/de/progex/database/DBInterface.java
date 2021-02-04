package de.progex.database;

import java.sql.Date;

import de.progex.model.Employee;
import de.progex.model.Leave;

public interface DBInterface {
	void start_employeeTable();

	void start_leaveTable();

	/*****************************************************************************/
	boolean createEmployee(Employee input);

	boolean updateEmployee(int employeeID, Employee input);

	boolean deleteEmployee(int employeeID);

	Employee getEmployeeById(int employeeID);

	Employee[] searchEmployee(String name);

	Employee[] getAllEmployee();

	/*****************************************************************************/
	boolean createLeave(int employeeID, Leave input);

	boolean deleteLeave(int employeeID, Date sdate, Date edate);

	Leave[] getLeave(int employeeID);

	Leave[] getAllLeaveByDate(Date start, Date end);

	/*****************************************************************************/
	void closeDB();
}
