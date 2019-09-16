package ua.com.hdcorp.hd.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.hdcorp.hd.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
}
