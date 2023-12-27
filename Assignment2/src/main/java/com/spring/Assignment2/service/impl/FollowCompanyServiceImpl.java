package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.FollowCompanyRepository;
import com.spring.Assignment2.entity.FollowCompany;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.FollowCompanyDTO;
import com.spring.Assignment2.model.SaveJobDTO;
import com.spring.Assignment2.service.FollowCompanyService;
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
public class FollowCompanyServiceImpl implements FollowCompanyService {
    @Autowired
    FollowCompanyRepository followCompanyRepository;
    @Override
    public List<FollowCompanyDTO> getFollowCompanyByUserId(int id) {
        List<FollowCompany> followCompanies = followCompanyRepository.getFollowCompanyByUserId(id);
        List<FollowCompanyDTO> followCompanyDTOs = new ArrayList<>();
        for (FollowCompany followCompany : followCompanies) {
            followCompanyDTOs.add(convertFollowCompany(followCompany));
        }
        return followCompanyDTOs;
    }

    @Override
    public void addfollowCompany(FollowCompanyDTO followCompanyDTO) {
        FollowCompany followCompany = convertFollowCompany1(followCompanyDTO);
        followCompanyRepository.save(followCompany);
    }

    /* hiển thị danh sách công ty đã theo dõi và phân trang*/
    @Override
    public Page<FollowCompany> getFollowCompanyPages(int id,Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<FollowCompany> followCompanies = followCompanyRepository.getFollowCompanyByUserIdPages(id,pageable);

        return followCompanies;
    }

    public FollowCompanyDTO convertFollowCompany(FollowCompany followCompany){
        FollowCompanyDTO followCompanyDTO = new FollowCompanyDTO();
        followCompanyDTO.setCompany(followCompany.getCompanies());
        followCompanyDTO.setUser(followCompany.getUser());
        return followCompanyDTO;
    }

    public FollowCompany convertFollowCompany1(FollowCompanyDTO followCompanyDTO){
        FollowCompany followCompany = new FollowCompany();
        followCompany.setCompanies(followCompanyDTO.getCompany());
        followCompany.setUser(followCompanyDTO.getUser());
        return followCompany;
    }
}
