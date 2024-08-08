package com.Niteesh.JobBoard.service;

import com.Niteesh.JobBoard.model.Company;
import com.Niteesh.JobBoard.model.Job;
import com.Niteesh.JobBoard.model.Review;
import com.Niteesh.JobBoard.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;
    private ReviewService reviewService;
    public CompanyService(CompanyRepository repository,ReviewService reviewService){
        this.companyRepository = repository;
        this.reviewService = reviewService;
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

    public void deleteCompanyById(Long companyId) {
        List<Review> reviews = companyRepository.findById(companyId).get().getReviews();
        for(Review review:reviews){
            reviewService.deleteReviewById(review.getId());
        }
        companyRepository.deleteById(companyId);
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
