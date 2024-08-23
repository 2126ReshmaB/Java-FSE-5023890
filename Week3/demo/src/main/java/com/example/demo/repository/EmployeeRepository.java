package com.example.demo.repository;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.projections.EmployeeNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Interface-based projection
    List<EmployeeNameProjection> findByDepartmentName(String departmentName);

    // Class-based projection using @Query with constructor expression
    @Query("SELECT new com.example.demo.dto.EmployeeDTO(e.firstName, e.lastName, d.name) " +
           "FROM Employee e JOIN e.department d WHERE d.name = :departmentName")
    List<EmployeeDTO> findEmployeeDTOByDepartmentName(@Param("departmentName") String departmentName);

    // Pagination and Sorting
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);
}

