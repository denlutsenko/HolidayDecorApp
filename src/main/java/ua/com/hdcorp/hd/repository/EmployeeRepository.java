package ua.com.hdcorp.hd.repository;


import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends RefreshableRepository<Employee, Long> {
    Employee findByEmail(String email);
}
