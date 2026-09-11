package com.project.first.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.first.Dao.EmployeeDao;
import com.project.first.Entity.Employee;

import jakarta.transaction.Transactional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	private EmployeeDao employeeDao;
	
	
	@Autowired
	public EmployeeServiceImpl (EmployeeDao theEmployeeDao) {
		employeeDao=theEmployeeDao;
	}
	@Override
	public List<Employee> findAll() {
		
		return employeeDao.findAll();
	}
	@Override
	public Employee findbyId(int theId) {
		Employee result=employeeDao.findbyId(theId);
		
		return result;
	}
	
	@Transactional
	@Override
	public Employee save(Employee theEmployee) {
		Employee dbEmployee= employeeDao.save(theEmployee);
				
		return dbEmployee;
	}
	@Transactional
	@Override
	public Employee deletebyId(int theId) {
		
		return employeeDao.deletebyId(theId);
	}

	
}
