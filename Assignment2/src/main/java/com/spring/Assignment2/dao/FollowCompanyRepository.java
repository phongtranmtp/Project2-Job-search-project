package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.FollowCompany;
import com.spring.Assignment2.entity.SaveJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowCompanyRepository extends JpaRepository<FollowCompany, Integer> {
    @Query(value = "select f.* from follow_company f\n" +
            "where f.user_id = ?",nativeQuery = true)
    List<FollowCompany> getFollowCompanyByUserId(int id);

    @Query(value = "select f.* from follow_company f\n" +
            "where f.user_id = ?",nativeQuery = true)
    Page<FollowCompany> getFollowCompanyByUserIdPages(int id, Pageable pageable);
}