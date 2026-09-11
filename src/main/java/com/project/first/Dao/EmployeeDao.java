package com.project.first.Dao;

import java.util.List;

import com.project.first.Entity.Employee;

public interface EmployeeDao {
	
	
	List<Employee> findAll();
	
	Employee findbyId(int theId);
	
	Employee save(Employee theEmployee);
	
	Employee deletebyId(int theId);
}



