package com.spring.Assignment2.service;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.RecruitmentDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecruitmentService {
    List<RecruitmentDTO> getRecuitments();

    Page<Recruitment> getRecuitmentPages(Integer page);

    void postJob(RecruitmentDTO recruitmentDTO);

    RecruitmentDTO detail(int id);

    void deletePostJob(int id);

    void update(RecruitmentDTO recruitmentDTO);

    List<RecruitmentDTO> getRecruimentByApplyPost();


    Page<Recruitment> findWorks(String keySearch,Integer page);
    
    Page<Recruitment> findByCompanyNameCompany(String keySearch, Integer page);


    Page<Recruitment> findByAddressWorks(String keySearch, Integer page);

    Page<Recruitment> getApplyRecruitmentPageByUserId(int id, Integer page);


    Page<Recruitment> getListRecruitmentByCompany(int id, Integer page);
}
