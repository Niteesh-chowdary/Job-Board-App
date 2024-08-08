package com.Niteesh.JobBoard.service;

import com.Niteesh.JobBoard.model.Company;
import com.Niteesh.JobBoard.model.Job;
import com.Niteesh.JobBoard.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    public CompanyService(CompanyRepository repository){
        this.companyRepository = repository;
    }
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public String createCompany(Company company) {
        Company companyCreated = companyRepository.save(company);
        if(companyCreated != null)
            return "COMPANY CREATED";
        else return "COMPANY CREATION FAILED";
    }

    public Company findCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public String deleteCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        if(company == null){
            return "NO ITEM";
        }
        else {
            companyRepository.deleteById(companyId);
            return "SUCCESSFULLY DELETED";
        }
    }

    public String updateCompanyById(Long companyId, Company company) {
        Company companyCreated = companyRepository.findById(companyId).orElse(null);
        if(companyCreated != null) {
            companyRepository.save(company);
            return "COMPANY UPDATED";
        }
        else return "COMPANY UPDATE FAILED";
    }
}
