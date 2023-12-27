package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.ApplyPost;
import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyPostRepository extends JpaRepository<ApplyPost, Integer> {
    @Query(value = "select p.*,count(recruitment_id) from applypost p\n" +
            "join recruitment r on \n" +
            "p.recruitment_id = r.id \n" +
            "group by recruitment_id\n" +
            "order by count(recruitment_id) desc",nativeQuery = true)
    List<ApplyPost> getsApplyPostByRe();

    @Query(value = "select applypost.* from applypost\n" +
            "join recruitment on\n" +
            "applypost.recruitment_id = recruitment.id\n" +
            "where recruitment.id =?",nativeQuery = true)
    List<ApplyPost> getApplyPostsByReId(int id);

    @Query(value = "select a.* from applypost a\n" +
            "where a.user_id = ?",nativeQuery = true)
    List<ApplyPost> getApplyPostByUserId(int id);

}