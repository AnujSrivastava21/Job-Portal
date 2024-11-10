package com.jobProject.JobProject.companies.impl;

import com.jobProject.JobProject.Job.Job;
import com.jobProject.JobProject.companies.Company;
import com.jobProject.JobProject.companies.CompanyRepository;
import com.jobProject.JobProject.companies.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImplementation implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);  // Fix syntax error

        if (companyOptional.isPresent()) {
            Company company2 = companyOptional.get();
            company2.setLocation(company.getLocation());
            company2.setDescription(company.getDescription());
            company2.setName(company.getName());

            companyRepository.save(company2);  // Save the updated company2 to persist changes
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }



}
