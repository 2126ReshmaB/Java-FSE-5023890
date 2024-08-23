package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.projections.EmployeeNameProjection;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Endpoint for interface-based projection
    @GetMapping("/names")
    public List<EmployeeNameProjection> getEmployeeNames(@RequestParam String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    // Endpoint for class-based projection
    @GetMapping("/dto")
    public List<EmployeeDTO> getEmployeeDTO(@RequestParam String departmentName) {
        return employeeRepository.findEmployeeDTOByDepartmentName(departmentName);
    }

    // Pagination and Sorting
    @GetMapping("/paginated")
    public Page<Employee> getEmployeesByDepartmentName(@RequestParam String departmentName, Pageable pageable) {
        return employeeRepository.findByDepartmentName(departmentName, pageable);
    }
}
