package com.example.lesson8.service.impl;

import com.example.lesson8.dto.CompanyDto;
import com.example.lesson8.entity.Company;
import com.example.lesson8.repository.CompanyRepository;
import com.example.lesson8.service.CompanyService;
import com.example.lesson8.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company create(CompanyDto companyDto) {
        Company company= new Company();
        company.setName(companyDto.getName());
        return  companyRepository.save(company);
    }

    @Override
    public List<Company> reads() {
        return companyRepository.findAll();
    }

    @Override
    public Company read(int id) throws Exception {
        return companyRepository.findById(id).orElseThrow(() ->{
            return  new Exception("Not found");
        });
    }

    @Override
    public Company update(int id,CompanyDto companyDto) throws Exception {
        Optional<Company> company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(() -> {
            return new Exception("Not found");
        }));
        company.get().setName(companyDto.getName());
        return companyRepository.save(company.get());
    }

    @Override
    public void delete(int id) throws Exception {
        Optional<Company> company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(() -> {
            return new Exception();
        }));
        companyRepository.deleteById(id);
    }
}
