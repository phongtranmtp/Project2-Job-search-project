package com.spring.Assignment2.dao;

import com.spring.Assignment2.entity.SaveJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SaveJobRepository extends JpaRepository<SaveJob, Integer> {
    @Query(value = "select s.* from save_job s\n" +
            "where s.user_id = ?",nativeQuery = true)
    List<SaveJob> getSaveJobByUserId(int id);

    @Query(value = "select s.* from save_job s\n" +
            "where s.user_id = ?",nativeQuery = true)
    Page<SaveJob> getSaveJobByUserIdPages(int id, Pageable pageable);
}