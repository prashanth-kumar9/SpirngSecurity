package com.project.first.Service;

import java.util.List;

import com.project.first.Entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findbyId(int theId);

	Employee save(Employee theEmployee);

	Employee deletebyId(int theId);
}
