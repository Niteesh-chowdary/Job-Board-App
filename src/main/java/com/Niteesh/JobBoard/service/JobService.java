package com.Niteesh.JobBoard.service;

import com.Niteesh.JobBoard.model.Job;
import com.Niteesh.JobBoard.repository.JobRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    JobRepository repository;

    public JobService(JobRepository repository){
        this.repository = repository;
    }

    public List<Job> findAll(){
        return repository.findAll();
    }

    public String createJob(Job job){
        Job jobCreated = repository.save(job);
        if(jobCreated != null)
            return "JOB CREATED";
        else return "JOB CREATION FAILED";
    }

    public Job findJob(Long jobId) {
        return repository.findById(jobId).orElse(null);
    }

    public void deleteJobById(Long jobId) {
        repository.deleteById(jobId);
    }

    public String updateJobById(Long jobId, Job job) {
        Job jobCreated = repository.findById(jobId).orElse(null);
        if(jobCreated != null) {
            repository.save(job);
            return "JOB UPDATED";
        }
        else return "JOB UPDATE FAILED";
    }
}
