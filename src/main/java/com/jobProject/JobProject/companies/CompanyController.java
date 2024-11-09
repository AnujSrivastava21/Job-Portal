package com.jobProject.JobProject.companies;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean isUpdated = companyService.updateCompany(company, id);
        if (isUpdated) {
            return new ResponseEntity<>("Company Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company Added Successfully", HttpStatus.CREATED);
    }
}
