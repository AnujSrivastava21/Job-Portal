package com.jobProject.JobProject.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;
    private long nextId = 1L;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        System.out.println("Return All jobs data Successfully");
        return  ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> CreateJob(@RequestBody Job job) {
        job.setId(nextId++);
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully" , HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable  Long id){
        Job jobId = jobService.getJobById(id);
        if(jobId!=null){
            return new ResponseEntity<>(jobId , HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Job> deleteById(@PathVariable Long id){
        Job deletedId = jobService.deleteById(id);
        if(deletedId!=null){
            return new ResponseEntity<>(deletedId ,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updatedJob(@PathVariable Long id , @RequestBody Job updatedJob){
        boolean updated = jobService.updatedJob(id , updatedJob);
        if(updated){
            return new ResponseEntity<>("Updated Successfully" , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
