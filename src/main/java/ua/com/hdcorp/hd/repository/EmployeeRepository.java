package ua.com.hdcorp.hd.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.hdcorp.hd.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends RefreshableRepository<Employee, Long> {
    Employee findByEmail(String email);

    @Query(value = "SELECT * FROM employees WHERE status='ACTIVE'", nativeQuery = true)
    List<Employee> findAllActiveEmployees();

    @Query(value = "SELECT * FROM employees WHERE id=?1 And status=?2", nativeQuery = true)
    Optional<Employee> findByIdAndActiveStatus(Long id, String status);

}
