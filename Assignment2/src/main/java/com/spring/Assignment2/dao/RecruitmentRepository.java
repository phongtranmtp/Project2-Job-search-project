package com.spring.Assignment2.dao;

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
public interface RecruitmentRepository extends JpaRepository<Recruitment,Integer> {
    @Query(value = "select r.*,count(p.recruitment_id) from recruitment r\n" +
            "join applypost p on \n" +
            "r.id = p.recruitment_id \n" +
            "where r.salary > 5000000\n" +
            "group by p.recruitment_id \n" +
            "order by count(p.recruitment_id) desc limit 3",nativeQuery = true)
    List<Recruitment> getRecruimentByApplyPost();

    @Query(value = "select r.* from recruitment r\n" +
                "where r.title like %?%",nativeQuery = true)
    Page<Recruitment> findWorks(String keySearch,Pageable pageable);

    Page<Recruitment> findByCompanyNameCompany(String nameCompany, Pageable pageable);

    Page<Recruitment> findByCompanyAddress(String address, Pageable pageable);

    @Query(value = "select r.* from recruitment r \n" +
            "inner join applypost a on \n" +
            "r.id = a.recruitment_id \n" +
            "where a.user_id = ?",
    countQuery = "select r.* from recruitment r \n" +
            "inner join applypost a on \n" +
            "r.id = a.recruitment_id \n" +
            "where a.user_id = ?",nativeQuery = true)
    Page<Recruitment> getApplyRecruitmentByUserId(int id, Pageable pageable);

    @Query(value = "select r.* from company c\n" +
            "join recruitment r on \n" +
            "c.id = r.company_id \n" +
            "where c.id = ?",
            countQuery = "select r.* from company c\n" +
                    "join recruitment r on \n" +
                    "c.id = r.company_id \n" +
                    "where c.id = ?",nativeQuery = true)
    Page<Recruitment> getListRecruitmentByCompany(int id, Pageable pageable);
}
