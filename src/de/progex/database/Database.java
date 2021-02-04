package de.progex.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.progex.model.Employee;
import de.progex.model.Leave;

/*
 * Add following external .jar to build path:  
 * db-derby-10.14.2.0-lib/lib/derby.jar
 * db-derby-10.14.2.0-lib/lib/derbyclient.jar
 */

public class Database implements DBInterface {
	private final static String url = "jdbc:derby:directory:database/employeedb;";

	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection connection = DriverManager.getConnection(url + "create=true");
		return connection;
	}

	@Override
	public void start_employeeTable() {
		String create_employee_statement = "CREATE TABLE EMPLOYEE ("
				+ "ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY, " + "FNAME VARCHAR(30), " + "LNAME VARCHAR(30), "
				+ "EMAIL VARCHAR(50), " + "STREETNUM VARCHAR(50), " + "CITY VARCHAR(50), " + "SKILLS VARCHAR(100), "
				+ "DEPARTMENT VARCHAR(50), " + "SALARY DOUBLE PRECISION, " + "PRIMARY KEY(ID) " + ")";

		try (Connection connection = Database.createConnection()) {
			// Create Table if not defined
			DatabaseMetaData metaDaten = connection.getMetaData();
			ResultSet tabellen = metaDaten.getTables(null, "APP", "EMPLOYEE", null);
			if (!tabellen.next()) {
				Statement stmt = connection.createStatement();
				stmt.executeUpdate(create_employee_statement);
				stmt.close();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
	}

	@Override
	public void start_leaveTable() {
		String create_leave_statement = "CREATE TABLE LEAVE (" + "ID INT NOT NULL GENERATED BY DEFAULT AS IDENTITY, "
				+ "STATUS VARCHAR(30), " + "SDATE DATE, " + "EDATE DATE, " + "CONSTRAINT ADS_PK " // PRIMARY KEY (ID),
				+ "FOREIGN KEY (ID) REFERENCES EMPLOYEE(ID) ON DELETE CASCADE " + ")";

		try (Connection connection = Database.createConnection()) {
			// Create Table if not defined
			DatabaseMetaData metaDaten = connection.getMetaData();
			ResultSet tabellen = metaDaten.getTables(null, "APP", "LEAVE", null);
			if (!tabellen.next()) {
				Statement anweisung = connection.createStatement();
				anweisung.executeUpdate(create_leave_statement);
				anweisung.close();
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
	}

	/*****************************************************************************/
	@Override
	public boolean createEmployee(Employee input) {
		String STMT = ("INSERT INTO EMPLOYEE VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (Connection connection = Database.createConnection()) {
			PreparedStatement insertStmt = connection.prepareStatement(STMT);
			insertStmt.setString(1, input.getFirstName());
			insertStmt.setString(2, input.getLastName());
			insertStmt.setString(3, input.getEmail());
			insertStmt.setString(4, input.getStreetAndNumber());
			insertStmt.setString(5, input.getCity());
			insertStmt.setString(6, input.getSkills());
			insertStmt.setString(7, input.getDepartment());
			insertStmt.setDouble(8, input.getSalary());
			insertStmt.executeUpdate();
			insertStmt.close();
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmployee(int employeeID, Employee input) {
		String STMT = ("UPDATE EMPLOYEE SET FNAME = ?, LNAME = ?, EMAIL = ?, STREETNUM = ?, CITY = ?, SKILLS = ?, DEPARTMENT = ?, SALARY = ? WHERE EMPLOYEE.ID = ?");

		try (Connection connection = Database.createConnection()) {
			PreparedStatement stmt = connection.prepareStatement(STMT);
			stmt.setString(1, input.getFirstName());
			stmt.setString(2, input.getLastName());
			stmt.setString(3, input.getEmail());
			stmt.setString(4, input.getStreetAndNumber());
			stmt.setString(5, input.getCity());
			stmt.setString(6, input.getSkills());
			stmt.setString(7, input.getDepartment());
			stmt.setDouble(8, input.getSalary());
			stmt.setInt(9, employeeID);
			stmt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int employeeID) {
		String STMT = "DELETE FROM EMPLOYEE WHERE EMPLOYEE.ID = ?";

		try (Connection connection = Database.createConnection()) {
			PreparedStatement stmt = connection.prepareStatement(STMT);
			stmt.setInt(1, employeeID);
			stmt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee getEmployeeById(int employeeID) {
		String STMT = ("SELECT * FROM EMPLOYEE WHERE EMPLOYEE.ID = ?");

		try (Connection connection = Database.createConnection()) {
			// show date
			PreparedStatement getStmt = connection.prepareStatement(STMT);
			getStmt.setInt(1, employeeID);
			ResultSet result = getStmt.executeQuery();
			while (result.next()) {
				Employee e = new Employee();

				e.setEmployee_id(result.getInt("ID"));
				e.setFirstName(result.getString("FNAME"));
				e.setLastName(result.getString("LNAME"));
				e.setEmail(result.getString("EMAIL"));
				e.setStreetAndNumber(result.getString("STREETNUM"));
				e.setCity(result.getString("CITY"));
				e.setSkills(result.getString("SKILLS"));
				e.setDepartment(result.getString("DEPARTMENT"));
				e.setSalary(result.getDouble("SALARY"));

				return e;
			}
			getStmt.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee[] searchEmployee(String word) {
		String STMT = ("SELECT * FROM EMPLOYEE WHERE EMPLOYEE.LNAME LIKE ? OR EMPLOYEE.FNAME LIKE ? OR EMPLOYEE.SKILLS LIKE ? OR EMPLOYEE.CITY LIKE ? OR EMPLOYEE.DEPARTMENT LIKE ?");
		Employee[] employeeArr = new Employee[100];
		int i = 0;
		try (Connection connection = Database.createConnection()) {
			// show date
			PreparedStatement getStmt = connection.prepareStatement(STMT);
			getStmt.setString(1, "%" + word + "%");
			getStmt.setString(2, "%" + word + "%");
			getStmt.setString(3, "%" + word + "%");
			getStmt.setString(4, "%" + word + "%");
			getStmt.setString(5, "%" + word + "%");
			ResultSet result = getStmt.executeQuery();
			while (result.next()) {
				Employee e = new Employee();

				e.setEmployee_id(result.getInt("ID"));
				e.setFirstName(result.getString("FNAME"));
				e.setLastName(result.getString("LNAME"));
				e.setEmail(result.getString("EMAIL"));
				e.setStreetAndNumber(result.getString("STREETNUM"));
				e.setCity(result.getString("CITY"));
				e.setSkills(result.getString("SKILLS"));
				e.setDepartment(result.getString("DEPARTMENT"));
				e.setSalary(result.getDouble("SALARY"));

				employeeArr[i] = e;
				i++;
			}
			getStmt.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return employeeArr;
	}

	@Override
	public Employee[] getAllEmployee() {
		String STMT = ("SELECT * FROM EMPLOYEE");
		Employee[] employeeArr = new Employee[100];
		int i = 0;
		try (Connection connection = Database.createConnection()) {
			PreparedStatement getStmt = connection.prepareStatement(STMT);
			ResultSet result = getStmt.executeQuery();
			
			while (result.next()) {
				Employee e = new Employee();

				e.setEmployee_id(result.getInt("ID"));
				e.setFirstName(result.getString("FNAME"));
				e.setLastName(result.getString("LNAME"));
				e.setEmail(result.getString("EMAIL"));
				e.setStreetAndNumber(result.getString("STREETNUM"));
				e.setCity(result.getString("CITY"));
				e.setSkills(result.getString("SKILLS"));
				e.setDepartment(result.getString("DEPARTMENT"));
				e.setSalary(result.getDouble("SALARY"));

				employeeArr[i] = e;
				i++;
			}
			
			getStmt.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return employeeArr;
	}

	/*****************************************************************************/

	@Override
	public boolean createLeave(int employeeID, Leave input) {
		String STMT = ("INSERT INTO LEAVE (STATUS, SDATE, EDATE, ID) SELECT ?, ?, ?, E.ID FROM EMPLOYEE E WHERE E.ID = ?");
		try (Connection connection = Database.createConnection()) {
			PreparedStatement stmt = connection.prepareStatement(STMT);
			stmt.setString(1, input.getStatus());
			stmt.setDate(2, input.getStartDate());
			stmt.setDate(3, input.getEndDate());
			stmt.setInt(4, employeeID);
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteLeave(int employeeID, Date sdate, Date edate) {
		String STMT = "DELETE FROM LEAVE WHERE LEAVE.ID = ? AND LEAVE.SDATE = ? AND LEAVE.EDATE = ?";

		try (Connection connection = Database.createConnection()) {
			PreparedStatement stmt = connection.prepareStatement(STMT);
			stmt.setInt(1, employeeID);
			stmt.setDate(2, sdate);
			stmt.setDate(3, edate);
			stmt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Leave[] getLeave(int employeeID) {
		String STMT = ("SELECT * FROM LEAVE WHERE LEAVE.ID = ?");
		Leave[] leaveArr = new Leave[100];
		int i = 0;
		try (Connection connection = Database.createConnection()) {
			PreparedStatement getStmt = connection.prepareStatement(STMT);
			getStmt.setInt(1, employeeID);
			ResultSet result = getStmt.executeQuery();
			while (result.next()) {
				Leave le = new Leave();

				le.setLeave_id(result.getInt("ID"));
				le.setStatus(result.getString("STATUS"));
				le.setStartDate(result.getDate("SDATE"));
				le.setEndDate(result.getDate("EDATE"));

				leaveArr[i] = le;
				i++;
			}
			getStmt.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return leaveArr;
	}

	@Override
	public Leave[] getAllLeaveByDate(Date start, Date end) {
		String STMT = ("SELECT * FROM LEAVE WHERE SDATE >= ? AND EDATE <= ?");
		Leave[] leaveArr = new Leave[100];
		int i = 0;
		try (Connection connection = Database.createConnection()) {
			// show date
			PreparedStatement getStmt = connection.prepareStatement(STMT);
			getStmt.setDate(1, start);
			getStmt.setDate(2, end);
			ResultSet result = getStmt.executeQuery();
			while (result.next()) {
				Leave le = new Leave();

				le.setLeave_id(result.getInt("ID"));
				le.setStatus(result.getString("STATUS"));
				le.setStartDate(result.getDate("SDATE"));
				le.setEndDate(result.getDate("EDATE"));

				leaveArr[i] = le;
				i++;
			}
			getStmt.close();
			result.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Datenbank-Treiber nicht gefunden");
		} catch (SQLException e) {
			System.err.println("SQL Fehler");
			e.printStackTrace();
		}
		return leaveArr;
	}

	/*****************************************************************************/

	@Override
	public void closeDB() {
		try {
			DriverManager.getConnection(url + "shutdown=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}