package com.example.lesson8.controller;

import com.example.lesson8.dto.JobDto;
import com.example.lesson8.entity.Employee;
import com.example.lesson8.entity.Job;
import com.example.lesson8.repository.EmployeeRepository;
import com.example.lesson8.repository.JobRepository;
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
