package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.*;
import com.spring.Assignment2.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CompanyServive companyServive;

    @Autowired
    CvService cvService;

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    ApplyPostService applyPostService;

    /* Hiển thị thông tin user */
    @GetMapping("/profile")
    public String profile(Model theModel){

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        userDTO.setEmail(currentUser.getEmail());
        userDTO.setAddress(currentUser.getAddress());
        userDTO.setPhoneNumber(currentUser.getPhoneNumber());
        userDTO.setDescription(currentUser.getDescription());
        userDTO.setStatus(currentUser.getStatus());
        userDTO.setImage(currentUser.getImage());
        userDTO.setCv(currentUser.getCv());
        theModel.addAttribute("user",userDTO);

        CompanyDTO companyDTO = companyServive.getCompanyByUserId(userDTO.getId());
        theModel.addAttribute("companyInformation",companyDTO);

        CvDTO cvDTO = cvService.getCvByUserId(userDTO.getId());
        theModel.addAttribute("Cv",cvDTO);

        List<RoleDTO> roleDTOs = roleService.getRoles();
        theModel.addAttribute("roleDTOs",roleDTOs);


        return "public/profile";
    }

    /* Cập nhập thông tin user */
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("userDTO") UserDTO userDTO) throws IOException {
        userService.updateProfile(userDTO);

        return "redirect:/auth/logout";
    }

    /* Gửi email khi đăng ký để xác thực tài khoản */
    @PostMapping("/confirm-account")
    public String confirmAccount(@ModelAttribute("userDTO") UserDTO userDTO) throws IOException {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        userDTO.setStatus(1);
        userService.confirmAccount(userDTO);

        return "redirect:/auth/logout";
    }

    /* Danh sách công việc user có thể ứng tuyển */
    @GetMapping("/worklist")
    public String workList(Model theModel,@RequestParam(value = "page",defaultValue = "1") Integer page){
        Page<Recruitment> recruitmentDTOs = recruitmentService.getRecuitmentPages(page);
        theModel.addAttribute("recruitmentDTOs",recruitmentDTOs);
        theModel.addAttribute("totalPages",recruitmentDTOs.getTotalPages());
        theModel.addAttribute("currentPage",page);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        theModel.addAttribute("user",userDTO);

        List<CompanyDTO> companyDTOs = companyServive.getCompanies();
        theModel.addAttribute("companyDTOs",companyDTOs);

        return "public/recruitment";
    }



}
