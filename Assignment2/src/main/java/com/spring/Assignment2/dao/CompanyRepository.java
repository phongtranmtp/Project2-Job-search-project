package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.model.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    @Query(value = "select company.*, count(company.id) from company\n" +
            "join recruitment on \n" +
            "company.id = recruitment.company_id \n" +
            "join applypost on\n" +
            "applypost.recruitment_id = recruitment.id \n" +
            "group by company.id \n" +
            "order by count(company.id) desc limit 3",nativeQuery = true)
    List<Company> getcompanyByApplyPost();

    @Query(value = "select c.* from company c\n" +
            "where c.user_id = ?",nativeQuery = true)
    Company getcompanyByUserId(int id);


    Company findCompanyByEmail(String email);


}
