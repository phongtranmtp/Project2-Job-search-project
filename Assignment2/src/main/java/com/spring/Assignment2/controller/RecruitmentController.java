package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.model.*;
import com.spring.Assignment2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
//@RequestMapping("/recruitment")
public class RecruitmentController {

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CompanyServive companyServive;

    @Autowired
    ApplyPostService applyPostService;

    /* hiển thị danh sách các bài đăng */
    @GetMapping("/user/list-post")
    public String getPostList(Model theModel, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Page<Recruitment> recruitmentDTOs = recruitmentService.getRecuitmentPages(page);
        theModel.addAttribute("recruitmentDTOs", recruitmentDTOs);
        theModel.addAttribute("totalPages", recruitmentDTOs.getTotalPages());
        theModel.addAttribute("currentPage", page);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        theModel.addAttribute("user", userDTO);

        List<RoleDTO> roleDTOs = roleService.getRoles();
        theModel.addAttribute("roleDTOs", roleDTOs);

        return "public/post-list";
    }

    @GetMapping("/recruitment/post")
    public String postJob(Model theModel) {
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setCompany(currentUser.getCompany());
        userDTO.setRole(currentUser.getRoleId());
        userDTO.setImage(currentUser.getImage());
        userDTO.setEmail(currentUser.getEmail());
        userDTO.setStatus(currentUser.getStatus());
        theModel.addAttribute("user", userDTO);
        if (currentUser.getStatus() == 0) {
            return "public/profile";
        }
        List<CategoryDTO> categoryDTOs = categoryService.getCategories();
        theModel.addAttribute("categoryDTOs", categoryDTOs);

        return "public/post-job";
    }

    /* Tạo 1 bài đăng mới */
    @PostMapping("/recruitment/add")
    public String addPostJob(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO) {
        recruitmentService.postJob(recruitmentDTO);
        return "redirect:/user/list-post";
    }

    /* xem chi tiết bài đăng */
    @GetMapping("/recruitment/detail/{id}")
    public String detailPost(@PathVariable("id") int id, Model theModel) {
        RecruitmentDTO recruitmentDTO = recruitmentService.detail(id);
        theModel.addAttribute("recruitmentDTOs", recruitmentDTO);

        List<CategoryDTO> categoryDTOs = categoryService.getCategories();
        theModel.addAttribute("categoryDTOs", categoryDTOs);

        List<CompanyDTO> companyDTOs = companyServive.getCompanies();
        theModel.addAttribute("companyDTOs", companyDTOs);

        List<ApplyPostDTO> applyPostDTOs = applyPostService.getApplyPostsByReId(id);
        theModel.addAttribute("applyPosts", applyPostDTOs);

        List<UserDTO> userDTOs = userService.getUsers();
        theModel.addAttribute("userDTOs", userDTOs);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        theModel.addAttribute("user", userDTO);
        return "public/detail-post";
    }

    /* Hiển thị các thông của bài đăng để cập nhập */
    @GetMapping("/recruitment/editpost/{id}")
    public String editJob(@PathVariable("id") int id, Model theModel) {
        RecruitmentDTO recruitmentDTO = recruitmentService.detail(id);
        theModel.addAttribute("recruitmentDTOs", recruitmentDTO);

        List<CategoryDTO> categoryDTOs = categoryService.getCategories();
        theModel.addAttribute("categories", categoryDTOs);


        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        theModel.addAttribute("user", userDTO);

        List<RoleDTO> roleDTOs = roleService.getRoles();
        theModel.addAttribute("roleDTOs", roleDTOs);
        return "public/edit-job";
    }

    /* Cập nhập thông tin cho bài đăng */
    @PostMapping("/recruitment/editpost")
    public String editPost(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO) {
        recruitmentService.update(recruitmentDTO);

        return "redirect:/user/list-post";
    }

    /* Xóa bài đăng */
    @PostMapping("/recruitment/delete")
    public String deletePostJob(@RequestParam("id") int id) {
        recruitmentService.deletePostJob(id);
        return "redirect:/user/list-post";
    }

    /* Tìm kiếm theo tên công việc */
    @GetMapping("/user/search")
    public String findWork(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           Model model) {
        Page<Recruitment> recruitments = recruitmentService.findWorks(recruitmentDTO.getKeySearch(),page);
        model.addAttribute("recruitments",recruitments);
        model.addAttribute("totalPages", recruitments.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("recruitmentDTO",recruitmentDTO);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        userDTO.setCompany(currentUser.getCompany());
        model.addAttribute("user", userDTO);

        return "public/result-search";
    }

    /* Tìm kiếm theo tên công ty */
    @GetMapping("/user/searchByNameCompany")
    public String findByNameCompanyWork(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           Model model) {
        Page<Recruitment> recruitments = recruitmentService.findByCompanyNameCompany(recruitmentDTO.getKeySearch(),page);
        model.addAttribute("recruitments",recruitments);
        model.addAttribute("totalPages", recruitments.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("recruitmentDTO",recruitmentDTO);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        userDTO.setCompany(currentUser.getCompany());
        model.addAttribute("user", userDTO);

        return "public/result-search-company";
    }

    /* Tìm kiếm theo địa chỉ công ty */
    @GetMapping("/user/searchaddress")
    public String findByAddressWork(@ModelAttribute("recruitmentDTO") RecruitmentDTO recruitmentDTO,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        Model model) {
        Page<Recruitment> recruitments = recruitmentService.findByAddressWorks(recruitmentDTO.getKeySearch(),page);
        model.addAttribute("recruitments",recruitments);
        model.addAttribute("totalPages", recruitments.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("recruitmentDTO",recruitmentDTO);

        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        userDTO.setCompany(currentUser.getCompany());
        model.addAttribute("user", userDTO);

        return "public/result-search-address";
    }

}
