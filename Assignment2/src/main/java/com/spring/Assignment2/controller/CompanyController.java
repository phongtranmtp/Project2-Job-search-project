package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.CompanyDTO;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.CompanyServive;
import com.spring.Assignment2.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    CompanyServive companyServive;

    @Autowired
    RecruitmentService recruitmentService;

    /* hiển thị danh sách các công ty */
    @GetMapping("/company/list")
    public String getCompanies(Model theModel){
        List<CompanyDTO> companyDTOs = companyServive.getCompanies();
        theModel.addAttribute("companies",companyDTOs);
        return "public/home";
    }

    /* Xem chi tiết công ty */
    @GetMapping("/user/detail-company/{id}")
    public String detailCompany(@PathVariable("id") int id,Model model){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        model.addAttribute("user",userDTO);
        CompanyDTO companyDTO = companyServive.detailCompany(id);
        model.addAttribute("companyDTO",companyDTO);
        return "public/detail-company";
    }

    /* Cập nhập thông tin cho công ty */
    @PostMapping("/user/update-company")
    public String updateCompany(@ModelAttribute("companyDTO") CompanyDTO companyDTO) {

        companyServive.updateCompany(companyDTO);
        return "redirect:/auth/logout";
    }

    /*Danh sách công việc của công ty*/
    @GetMapping("/user/company-post/{id}")
    public String getListRecruitmentByCompany(Model model , @RequestParam(value = "page",defaultValue = "1") Integer page,
                                              @PathVariable("id") int id){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        model.addAttribute("user",userDTO);

        CompanyDTO company = companyServive.detailCompany(id);

        Page<Recruitment> recruitments = recruitmentService.getListRecruitmentByCompany(company.getId(),page);
        model.addAttribute("recruitments",recruitments);
        model.addAttribute("totalPages",recruitments.getTotalPages());
        model.addAttribute("currentPage",page);
        return "public/list-re";
    }

}
