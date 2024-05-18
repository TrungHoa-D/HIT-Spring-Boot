package com.example.lesson8.service;

import com.example.lesson8.dto.CompanyDto;
import com.example.lesson8.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    Company create(CompanyDto companyDto);
    List<Company> reads();
    Company read(int id) throws Exception;
    Company update(int id,CompanyDto companyDto) throws Exception;
    void delete(int id) throws Exception;
}
