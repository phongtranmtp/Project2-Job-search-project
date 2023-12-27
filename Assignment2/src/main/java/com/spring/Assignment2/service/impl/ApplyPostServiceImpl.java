package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.ApplyPostRepository;
import com.spring.Assignment2.dao.RecruitmentRepository;
import com.spring.Assignment2.dao.UserRepository;
import com.spring.Assignment2.entity.*;
import com.spring.Assignment2.model.ApplyPostDTO;
import com.spring.Assignment2.service.ApplyPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ApplyPostServiceImpl implements ApplyPostService {

    @Autowired
    ApplyPostRepository applyPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecruitmentRepository recruitmentRepository;

    /* lấy ra danh sách user đã ứng tuyển và phân trang */
    @Override
    public Page<ApplyPost> getApplyPosts(Integer page) {
        Pageable pageable = PageRequest.of(page-1,5);
        Page<ApplyPost> applyPosts = applyPostRepository.findAll(pageable);
        return applyPosts;
    }

    /* lấy ra danh sách user đã ứng tuyển theo recruitment id */
    @Override
    public List<ApplyPostDTO> getApplyPostsByReId(int id) {
        List<ApplyPost> applyPosts = applyPostRepository.getApplyPostsByReId(id);
        List<ApplyPostDTO> applyPostDTOs = new ArrayList<>();
        for (ApplyPost applyPost : applyPosts) {
            applyPostDTOs.add(convertApp(applyPost));
        }
        return applyPostDTOs;
    }

    /* lấy ra danh sách user đã ứng tuyển theo user id */
    @Override
    public List<ApplyPostDTO> getApplyPostByUserId(int id) {

        List<ApplyPost> applyPosts = applyPostRepository.getApplyPostByUserId(id);
        List<ApplyPostDTO> applyPostDTOs = new ArrayList<>();
        for (ApplyPost applyPost : applyPosts) {
            applyPostDTOs.add(convertApp(applyPost));
        }
        return applyPostDTOs;
    }


    @Override
    public void addApplyPost(ApplyPostDTO applyPostDTO) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = myDateObj.format(myFormatObj);
        applyPostDTO.setCreatedAt(formattedDate);

        ApplyPost applyPost = convertApp1(applyPostDTO);
        applyPostRepository.save(applyPost);
    }


    @Override
    public List<ApplyPostDTO> getApplyPosts() {
        List<ApplyPost> applyPosts = applyPostRepository.findAll();
        List<ApplyPostDTO> applyPostDTOs = new ArrayList<>();
        for (ApplyPost applyPost : applyPosts) {
            applyPostDTOs.add(convertApp(applyPost));
        }
        return applyPostDTOs;
    }

    @Override
    public List<ApplyPostDTO> getsApplyPostByRe() {
        List<ApplyPost> applyPosts = applyPostRepository.getsApplyPostByRe();
        List<ApplyPostDTO> applyPostDTOs = new ArrayList<>();
        for (ApplyPost applyPost : applyPosts) {
            applyPostDTOs.add(convertApp(applyPost));
        }
        return applyPostDTOs;
    }

    public ApplyPostDTO convertApp(ApplyPost applyPost){
        ApplyPostDTO applyPostDTO = new ApplyPostDTO();
        applyPostDTO.setId(applyPost.getId());
        applyPostDTO.setCreatedAt(applyPost.getCreatedAt());
        applyPostDTO.setNameCv(applyPost.getNameCv());
        applyPostDTO.setStatus(applyPost.getStatus());
        applyPostDTO.setText(applyPost.getText());
        applyPostDTO.setUser(applyPost.getUser());
        applyPostDTO.setRecruitment(applyPost.getRecruitment());

        return applyPostDTO;
    }

    public ApplyPost convertApp1(ApplyPostDTO applyPostDTO){
        ApplyPost applyPost = new ApplyPost();
        applyPost.setId(applyPostDTO.getId());
        applyPost.setCreatedAt(applyPostDTO.getCreatedAt());
        applyPost.setNameCv(applyPostDTO.getNameCv());
        applyPost.setStatus(applyPostDTO.getStatus());
        applyPost.setText(applyPostDTO.getText());
        applyPost.setUser(applyPostDTO.getUser());
        applyPost.setRecruitment(applyPostDTO.getRecruitment());

        return applyPost;
    }
}
