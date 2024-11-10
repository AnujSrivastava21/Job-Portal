package com.jobProject.JobProject.companies;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();

    boolean updateCompany(Company company , Long id);

    void createCompany(Company company);

    boolean deleteById(Long id);

    Company getCompanyById(Long id);
}
