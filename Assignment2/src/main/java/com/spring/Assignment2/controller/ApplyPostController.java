package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.ApplyPost;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.ApplyPostDTO;
import com.spring.Assignment2.model.RecruitmentDTO;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.ApplyPostService;
import com.spring.Assignment2.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ApplyPostController {
    @Autowired
    ApplyPostService applyPostService;

    @Autowired
    RecruitmentService recruitmentService;

    /* hiển thị danh sách ứng cử viên */
    @GetMapping("/user/listuser")
    public String listUser(Model model, @RequestParam(value = "page",defaultValue = "1") Integer page){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        model.addAttribute("user",userDTO);

        Page<ApplyPost> applyPostDTOs = applyPostService.getApplyPosts(page);
        model.addAttribute("applyPostDTOs",applyPostDTOs);
        model.addAttribute("totalPages",applyPostDTOs.getTotalPages());
        model.addAttribute("currentPage",page);
        return "public/list-user";
    }

    /* hiển thị danh sách công việc đã ứng tuyển*/
    @GetMapping("/user/get-list-apply")
    public String listApplyJob(Model theModel, @RequestParam(value = "page",defaultValue = "1") Integer page){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        theModel.addAttribute("user",userDTO);

        Page<Recruitment> recruitments = recruitmentService.getApplyRecruitmentPageByUserId(currentUser.getId(),page);
        theModel.addAttribute("recruitments",recruitments);
        theModel.addAttribute("totalPages",recruitments.getTotalPages());
        theModel.addAttribute("currentPage",page);
        return "public/list-apply-job";
    }

}
