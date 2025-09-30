package net.projectsync.springsecurity.service;

import java.util.List;
import org.springframework.stereotype.Service;
import net.projectsync.springsecurity.entity.Employee;
import net.projectsync.springsecurity.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found for id: " + id));
	}

	public Employee updateEmployeeById(Employee employee, long id) {
		return employeeRepository.findById(id)
				.map(existingEmployee -> {
					existingEmployee.setName(employee.getName());
					existingEmployee.setRole(employee.getRole());
					return employeeRepository.save(existingEmployee);
				})
				.orElseThrow(() -> new RuntimeException("User not found for id: " + id));
	}

	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
	}

	public void deleteAllEmployees() {
		employeeRepository.deleteAll();
	}
}
