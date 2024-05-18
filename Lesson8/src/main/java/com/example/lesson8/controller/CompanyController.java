package com.example.lesson8.controller;

import com.example.lesson8.dto.CompanyDto;
import com.example.lesson8.entity.Company;
import com.example.lesson8.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/cos")
    public ResponseEntity<?> readCos() {
        return ResponseEntity.ok().body(companyRepository.findAll());
    }

    @PostMapping("/cos")
    public ResponseEntity<?> createCos(@RequestBody CompanyDto companyDto) {
        Company co =  new Company();
        co.setName(companyDto.getName());
        return ResponseEntity.ok().body(companyRepository.save(co));
    }

    @DeleteMapping("/cos/{id}")
    public ResponseEntity<?> deleteCos(@PathVariable int id) {
        companyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cos/{id}")
    public ResponseEntity<?> updateCos (@PathVariable int id, @RequestBody CompanyDto companyDto) {
        Optional<Company> curCo = companyRepository.findById(id);
        if (companyDto.getName()!=null)
            curCo.get().setName(companyDto.getName());
        return ResponseEntity.ok().body(companyRepository.save(curCo.get()));
    }
}
