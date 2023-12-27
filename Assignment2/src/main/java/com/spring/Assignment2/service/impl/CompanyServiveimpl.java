package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.CompanyRepository;
import com.spring.Assignment2.dao.UserRepository;
import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.entity.User;
import com.spring.Assignment2.model.CompanyDTO;
import com.spring.Assignment2.service.CompanyServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyServiveimpl implements CompanyServive {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserRepository userRepository;

    /* lấy ra danh sách công ty */
    @Override
    public List<CompanyDTO> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company company : companies) {
            companyDTOs.add(convertCompany(company));
        }
        return companyDTOs;
    }

    @Override
    public List<CompanyDTO> getcompanyByApplyPost() {
        List<Company> companies = companyRepository.getcompanyByApplyPost();
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company company : companies) {
            companyDTOs.add(convertCompany(company));
        }
        return companyDTOs;
    }

    /* chi tiết công ty */
    @Override
    public CompanyDTO detailCompany(int id) {
        Company company = companyRepository.findById(id).orElse(null);
        CompanyDTO companyDTO = convertCompany(company);
        return companyDTO;
    }

    /* cập nhập công ty */
    @Override
    public void updateCompany(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.getId()).orElse(null);
        if (company != null) {
            company.setEmail(companyDTO.getEmail());
            company.setNameCompany(companyDTO.getNameCompany());
            company.setAddress(companyDTO.getAddress());
            company.setPhoneNumber(companyDTO.getPhoneNumber());
            company.setDescription(companyDTO.getDescription());
            company.setStatus(1);
            companyRepository.save(company);
        }
    }

    /* lấy ra  công ty theo user id*/
    @Override
    public CompanyDTO getCompanyByUserId(int id) {
        Company company = companyRepository.getcompanyByUserId(id);
        if (company == null) {
            User user = userRepository.findById(id).orElse(null);
            if (user.getRole().getId() == 2){
                company = new Company();
                company.setUser(user);
                companyRepository.save(company);
            } else {
                return null;
            }
        }
        CompanyDTO companyDTO = convertCompany1(company);
        return companyDTO;
    }

    /* Cập nhập logo công ty */
    @Override
    public void updateLogo(CompanyDTO companyDTO) {
        Company company = companyRepository.findById(companyDTO.getId()).orElse(null);
        if (company != null) {
            company.setLogo(companyDTO.getLogo());
            companyRepository.save(company);
        }
    }


    public CompanyDTO convertCompany(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setAddress(company.getAddress());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setLogo(company.getLogo());
        companyDTO.setNameCompany(company.getNameCompany());
        companyDTO.setPhoneNumber(company.getPhoneNumber());
        companyDTO.setStatus(company.getStatus());
        companyDTO.setUser(company.getUser());
        companyDTO.setRecruitments(company.getRecruitments());
        int count = 0;
        for (Recruitment recruitment : company.getRecruitments()) {
            count += recruitment.getApplyPosts().size();
        }
        companyDTO.setQuantityApply(count);
        return companyDTO;
    }

    public CompanyDTO convertCompany1(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setAddress(company.getAddress());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setEmail(company.getEmail());
        companyDTO.setLogo(company.getLogo());
        companyDTO.setNameCompany(company.getNameCompany());
        companyDTO.setPhoneNumber(company.getPhoneNumber());
        companyDTO.setStatus(company.getStatus());
        companyDTO.setUser(company.getUser());
        companyDTO.setRecruitments(company.getRecruitments());

        return companyDTO;
    }
}
