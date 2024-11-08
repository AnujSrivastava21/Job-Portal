package com.jobProject.JobProject.Job.impl;


import com.jobProject.JobProject.Job.Job;
import com.jobProject.JobProject.Job.JobRepository;
import com.jobProject.JobProject.Job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImplementation implements JobService {
//    private final List<Job> jobs =new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImplementation(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(@RequestBody Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;  // Return true if deletion was successful
        } catch (Exception e) {
            return false;  // Return false if an exception occurs
        }
    }

    @Override
    public boolean updatedJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);  // Fix syntax error

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());

            jobRepository.save(job);  // Save the updated job to persist changes
            return true;
        }

        return false;  // Return false if the job with the specified ID was not found
    }
}
