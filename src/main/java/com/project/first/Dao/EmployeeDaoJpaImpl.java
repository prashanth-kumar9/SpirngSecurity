package com.project.first.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.first.Entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao{
	
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoJpaImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> theQuery=entityManager.createQuery("from Employee", Employee.class);
		
		
		List<Employee> employee= theQuery.getResultList();
		return employee;
	}
	
	@Override
	public Employee findbyId(int theId) {
		Employee theEmployee = entityManager.find(Employee.class, theId);
		return theEmployee;
	}
	@Override
	public Employee save(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		
		return dbEmployee;
	}
	@Override
	public Employee deletebyId(int theId) {
		Employee dbEmployee= entityManager.find(Employee.class, theId);
		entityManager.remove(dbEmployee);
		return null;
	}

}
