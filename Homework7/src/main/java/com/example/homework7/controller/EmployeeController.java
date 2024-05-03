package com.example.homework7.controller;

import com.example.homework7.dto.CompanyDto;
import com.example.homework7.dto.EmployeeDto;
import com.example.homework7.entity.Company;
import com.example.homework7.entity.Employee;
import com.example.homework7.repository.CompanyRepository;
import com.example.homework7.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/ems")
    public ResponseEntity<?> readEms() {
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @GetMapping("/ems/{name}")
    public ResponseEntity<?> findEms(@PathVariable String name){
        return ResponseEntity.ok().body(employeeRepository.findEmployeesByName(name));
    }
    @PostMapping("/ems")
    public ResponseEntity<?> createEms(@RequestBody EmployeeDto employeeDto) {
        Employee em= new Employee();
        em.setName(employeeDto.getName());
        Company companyFind=companyRepository.findById(employeeDto.getCompany_id()).orElseThrow(() -> {throw new RuntimeException();});
        em.setCompany(companyFind);
        return ResponseEntity.ok().body(employeeRepository.save(em));
    }

    @DeleteMapping("/ems/{id}")
    public ResponseEntity<?> deleteEms(@PathVariable int id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/ems/{id}")
    public ResponseEntity<?> updateEms (@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
        Optional<Employee> curEm = employeeRepository.findById(id);
        if(employeeDto.getName()!=null)
        curEm.get().setName(employeeDto.getName());
        Company companyFind=companyRepository.findById(employeeDto.getCompany_id()).orElseThrow(() -> {throw new RuntimeException();});
        curEm.get().setCompany(companyFind);
        return ResponseEntity.ok().body(employeeRepository.save(curEm.get()));
    }
}
