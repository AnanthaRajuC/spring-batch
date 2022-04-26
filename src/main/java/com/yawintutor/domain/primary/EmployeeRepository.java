package com.yawintutor.domain.primary;

import com.yawintutor.domain.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
