package com.example.homework7.controller;

import com.example.homework7.dto.EmployeeDto;
import com.example.homework7.dto.JobDto;
import com.example.homework7.entity.Company;
import com.example.homework7.entity.Employee;
import com.example.homework7.entity.Job;
import com.example.homework7.repository.CompanyRepository;
import com.example.homework7.repository.EmployeeRepository;
import com.example.homework7.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/jobs")
    public ResponseEntity<?> readJobs() {
        return ResponseEntity.ok().body(jobRepository.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<?> createJobs(@RequestBody JobDto jobDto) {
        Job job= new Job();
        job.setName(jobDto.getName());
        Employee employeeFind=employeeRepository.findById(jobDto.getEmployee_id()).orElseThrow(() -> {throw new RuntimeException();});
        job.setEmployee(employeeFind);
        return ResponseEntity.ok().body(jobRepository.save(job));
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJobs(@PathVariable int id) {
        jobRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<?> updateJobs (@PathVariable int id, @RequestBody JobDto jobDto) {
        Optional<Job> curJob = jobRepository.findById(id);
        if(jobDto.getName()!=null)
            curJob.get().setName(jobDto.getName());
        Employee employeeFind=employeeRepository.findById(jobDto.getEmployee_id()).orElseThrow(() -> {throw new RuntimeException();});
        curJob.get().setEmployee(employeeFind);
        return ResponseEntity.ok().body(jobRepository.save(curJob.get()));
    }
}
