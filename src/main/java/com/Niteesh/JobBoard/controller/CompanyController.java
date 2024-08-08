package com.Niteesh.JobBoard.controller;

import com.Niteesh.JobBoard.model.Company;
import com.Niteesh.JobBoard.model.Job;
import com.Niteesh.JobBoard.model.Review;
import com.Niteesh.JobBoard.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    private JobController jobController;

    public CompanyController(CompanyService service,JobController jobController){
        this.companyService = service;
        this.jobController = jobController;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        List<Company> companies = companyService.findAll();
        if(companies != null){
            return new ResponseEntity<>(companies,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        String companyPosted = companyService.createCompany(company);
        if(companyPosted.equals("Company CREATED"))
            return new ResponseEntity<>(companyPosted,HttpStatus.CREATED);
        else return new ResponseEntity<>(companyPosted,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Long companyId){
        Company company = companyService.findCompanyById(companyId);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long companyId){
        Company company = companyService.findCompanyById(companyId);
        if(company == null){
            return new ResponseEntity<>("NO ITEM",HttpStatus.NOT_FOUND);
        }
        else{
            List<Job> jobs = company.getJobs();
            for(Job job:jobs){
                jobController.deleteJobById(job.getId());
            }
            companyService.deleteCompanyById(companyId);
            return new ResponseEntity<>("SUCCESSFULLY DELETED", HttpStatus.OK);
        }
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long companyId, @RequestBody Company company){
        String companyUpdateStatus = companyService.updateCompanyById(companyId,company);
        if(companyUpdateStatus.equals("JOB UPDATED"))
            return new ResponseEntity<>(companyUpdateStatus,HttpStatus.OK);
        else return new ResponseEntity<>(companyUpdateStatus,HttpStatus.NOT_FOUND);
    }
}
