package net.danielwind.effcaching.recipe3.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import net.danielwind.effcaching.recipe3.dao.EmployeeDao;
import net.danielwind.effcaching.recipe3.domain.Employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("employeeDaoImpl")
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	private static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	
	@Autowired
	public EmployeeDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * @{inheritDoc}
	 */
	@Cacheable("employeeCache")
	public List<Employee> findAll() {
		log.info("--- Accessing Dao Layer: EmployeeDaoImpl.findAll() ---");
		String sql = "SELECT * FROM employees";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}
	
  @CacheEvict(value="employeeCache", allEntries=true)
	public void insert(Employee emp) {
	  String sql = "INSERT INTO EMPLOYEES " +
	      "(FIRSTNAME, LASTNAME, ROLE, DEPARTMENT, SALARY) VALUES (?, ?, ?, ?, ?)";
	 
	    getJdbcTemplate().update(sql, new Object[] { emp.getFirstName(),
	        emp.getLastName(),emp.getRole(), emp.getDepartment(), emp.getSalary()  
	    });
	}

  @Cacheable(value="employeeCache", key="#employeeId")
  public Employee get(String employeeId) {
    log.info("--- Accessing Dao Layer: EmployeeDaoImpl.get() ---");
    String sql = "SELECT * FROM employees WHERE ID = ?";
    return getJdbcTemplate().queryForObject(sql, new Object[] { employeeId}, new BeanPropertyRowMapper<Employee>(Employee.class));
  }
  
  

}
