package com.spring.Assignment2.service;

import com.spring.Assignment2.entity.FollowCompany;
import com.spring.Assignment2.model.FollowCompanyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FollowCompanyService {
    List<FollowCompanyDTO> getFollowCompanyByUserId(int id);

    void addfollowCompany(FollowCompanyDTO followCompanyDTO);

    Page<FollowCompany> getFollowCompanyPages(int id,Integer page);
}
