package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CVRepository extends JpaRepository<CV, Integer> {
    @Query(value = "select CV.* from CV \n" +
            "where CV.user_id = ?",nativeQuery = true)
    CV getCvByUserId(int id);
}