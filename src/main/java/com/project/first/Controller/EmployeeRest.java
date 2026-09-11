package com.project.first.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.first.Entity.Employee;
import com.project.first.Service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api")
public class EmployeeRest {

	private EmployeeService employeeService;

	private JsonMapper jsonmapper;

//	@Autowired
//	public EmployeeRest(EmployeeService theEmployeeService) {
//		employeeService = theEmployeeService;
//	}

	@Autowired
	public EmployeeRest(EmployeeService theEmployeeService, JsonMapper thejsonmapper) {
		employeeService = theEmployeeService;
		jsonmapper = thejsonmapper;
	}

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/employee/{empid}")
	public Employee findbyId(@PathVariable Integer empid) {

		Employee emp = employeeService.findbyId(empid);

		if (empid == null) {
			throw new RuntimeException("Employee id not found" + empid);

		}

		return emp;
	}

	@PostMapping("/employee/{empid}")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);

		return employeeService.save(theEmployee);

	}

	@DeleteMapping("/employee/{empid}")
	public String removeEmp(@PathVariable Integer empid) {
		
		Employee theEmployee= employeeService.findbyId(empid);
		
		if(theEmployee == null) {
			throw new RuntimeException("employee not found --" +  empid);
			
		}
		employeeService.deletebyId(empid);
		
		return "Deleted employee id -" + empid;

	}

	@PostMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		return employeeService.save(theEmployee);

	}

	// path mapping

	@PatchMapping("/employee/{employeeId}")
	public Employee partialUpdate(@PathVariable int employeeId, @RequestBody Map<String, Object> patchpayload) {
		Employee emp = employeeService.findbyId(employeeId);

		if (emp == null) {
			throw new RuntimeException("Employee id not found" + employeeId);

		}

		if (patchpayload.containsKey("id")) {
			throw new RuntimeException("Employee id not allowed in request body" + employeeId);
		}
		
		Employee patchedEmployee= jsonmapper.updateValue(emp, patchpayload);
		
		Employee saveEmp= employeeService.save(patchedEmployee);
		return saveEmp;
	}
	
	
}
