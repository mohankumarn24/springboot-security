package net.projectsync.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.projectsync.springsecurity.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
