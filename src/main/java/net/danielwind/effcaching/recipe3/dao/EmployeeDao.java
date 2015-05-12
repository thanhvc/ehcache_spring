package net.danielwind.effcaching.recipe3.dao;

import java.util.List;
import net.danielwind.effcaching.recipe3.domain.Employee;

public interface EmployeeDao {
	
	/**
	 * Simple method for retrieving all employees
	 * stored in the database.  
	 * @return Typed List of All Employees
	 */
	public List<Employee> findAll();
	
	/**
   * Simple method for retrieving the employee by Id
   * stored in the database.  
   * @return The employee
   */
  public Employee get(String employeeId);
	
	/**
	 * Simple method for inserting the 
	 * given Employees to store database
	 * 
	 * @param emp The Employee
	 */
	public void insert(Employee emp);
	
	
}
