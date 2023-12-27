package com.spring.Assignment2.controller;

import com.spring.Assignment2.model.*;
import com.spring.Assignment2.service.ApplyPostService;
import com.spring.Assignment2.service.CategoryService;
import com.spring.Assignment2.service.CompanyServive;
import com.spring.Assignment2.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CompanyServive companyServive;

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ApplyPostService applyPostService;

    @GetMapping("/home")
    public String home(Model theModel){
        List<CompanyDTO> companyDTOs = companyServive.getcompanyByApplyPost();
        theModel.addAttribute("companyDTOs",companyDTOs);

        List<RecruitmentDTO> recruitmentDTOs = recruitmentService.getRecruimentByApplyPost();
        theModel.addAttribute("recruitmentDTOs",recruitmentDTOs);

        List<CategoryDTO> categoryDTOs = categoryService.getTopCategory();
        theModel.addAttribute("categoryDTOs",categoryDTOs);

        List<ApplyPostDTO> applyPostDTOs = applyPostService.getsApplyPostByRe();
        theModel.addAttribute("applyPosts",applyPostDTOs);


        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            UserDTO userDTO = new UserDTO();
            userDTO.setId(currentUser.getId());
            userDTO.setFullName(currentUser.getName());
            userDTO.setRole(currentUser.getRoleId());
            theModel.addAttribute("user",userDTO);
        }

        return "public/home";
    }

}
