package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.FollowCompany;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.FollowCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FollowCompanyController {
    @Autowired
    FollowCompanyService followCompanyService;

    /* hiển thị danh sách công ty đã theo dõi và phân trang*/
    @GetMapping("/user/get-list-company")
    public String getListFollowCompany(Model model, @RequestParam(value = "page",defaultValue = "1") Integer page){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        model.addAttribute("user",userDTO);

        Page<FollowCompany> followCompanies = followCompanyService.getFollowCompanyPages(currentUser.getId(),page);
        model.addAttribute("followCompanies",followCompanies);
        model.addAttribute("totalPages",followCompanies.getTotalPages());
        model.addAttribute("currentPage",page);

        return "public/list-follow-company";
    }
}
