package com.spring.Assignment2.service;

import com.spring.Assignment2.entity.ApplyPost;
import com.spring.Assignment2.model.ApplyPostDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplyPostService {
    List<ApplyPostDTO> getApplyPosts();

    List<ApplyPostDTO> getsApplyPostByRe();

    Page<ApplyPost> getApplyPosts(Integer page);

    List<ApplyPostDTO> getApplyPostsByReId(int id);

    List<ApplyPostDTO> getApplyPostByUserId(int id);

    void addApplyPost(ApplyPostDTO applyPostDTO);

}
