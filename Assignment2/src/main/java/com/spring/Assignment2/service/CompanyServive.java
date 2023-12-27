package com.spring.Assignment2.service;

import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.model.CompanyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyServive {
    List<CompanyDTO> getCompanies();

    List<CompanyDTO> getcompanyByApplyPost();

    CompanyDTO detailCompany(int id);

    void updateCompany(CompanyDTO companyDTO);

    CompanyDTO getCompanyByUserId(int id);

    void updateLogo(CompanyDTO companyDTO);

}
