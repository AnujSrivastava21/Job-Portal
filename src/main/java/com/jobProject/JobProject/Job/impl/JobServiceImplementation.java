package com.jobProject.JobProject.Job.impl;


import com.jobProject.JobProject.Job.Job;
import com.jobProject.JobProject.Job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobServiceImplementation implements JobService {
    private final List<Job> jobs =new ArrayList<>();
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(@RequestBody Job job) {
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for(Job job: jobs){
            if(job.getId() == id){
                return job;
            }
        }
        return null;
    }

    @Override
    public Job deleteById(Long id) {
        for(Job job: jobs){
            if(job.getId() == id){
                jobs.remove(job);
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean updatedJob(Long id, Job updatedJob) {
        for (Job job:jobs){
            if(job.getId()==id){
                job.setTitle(updatedJob.getTitle());
               job.setDescription(updatedJob.getDescription());
               job.setMaxSalary(updatedJob.getMaxSalary());
               job.setMinSalary(updatedJob.getMinSalary());
               job.setLocation(updatedJob.getLocation());
               return true;
            }
        }
        return false;
    }
}
