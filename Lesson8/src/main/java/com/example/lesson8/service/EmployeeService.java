package com.example.lesson8.service;

import com.example.lesson8.dto.CompanyDto;
import com.example.lesson8.dto.EmployeeDto;
import com.example.lesson8.entity.Company;
import com.example.lesson8.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee create(EmployeeDto employeeDto);
    List<Employee> reads();
    Employee read(int id);
    Employee update(int id,EmployeeDto employeeDto);
    void delete(int id);
}
