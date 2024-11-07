package com.jobProject.JobProject.Job;


import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    Job deleteById(Long id);

    boolean updatedJob(Long id, Job updatedJob);
}
