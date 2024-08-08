package com.Niteesh.JobBoard.service;

import com.Niteesh.JobBoard.model.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    List<Job> jobs = new ArrayList<>();
    public List<Job> findAll(){
        return jobs;
    }

    public void createJob(Job job){
        jobs.add(job);
    }
}
