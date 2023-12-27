package com.spring.Assignment2.controller;

import com.spring.Assignment2.model.CompanyDTO;
import com.spring.Assignment2.model.RoleDTO;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.CompanyServive;
import com.spring.Assignment2.service.RoleService;
import com.spring.Assignment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    CompanyServive companyServive;

    /* đăng nhập */
    @GetMapping("/auth/login")
    public String login(Model theModel){
        List<CompanyDTO> companyDTOs = companyServive.getCompanies();
        theModel.addAttribute("companyDTOs", companyDTOs);

        List<RoleDTO> roleDTOs = roleService.getRoles();
        theModel.addAttribute("roleDTOs",roleDTOs);
        return "public/login";
    }

    /* đăng xuất*/
    @GetMapping("/auth/logout")
    public String logout(){

        return "redirect:/auth/login";
    }

    /* Đăng kí user */
    @PostMapping("/auth/register")
    public String register(@ModelAttribute("userDTO") UserDTO userDTO){
        /* kiểm tra email đã tồn tại hay chưa? */
        if (userService.checkEmail(userDTO.getEmail())){
            return "redirect:/auth/login?emailexist";
        }
        /* kiểm tra mật khẩu */
        if (userDTO.getPassword().equals(userDTO.getCheckPass())==false){
            return "redirect:/auth/login?chekcpass";
        }
        userService.add(userDTO);
        return "redirect:/auth/login?success";
    }

    /* đăng nhập sai role */
    @GetMapping("/access-denied")
    public String showAccessDenied(Model theModel){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        theModel.addAttribute("user",userDTO);
        return "public/access-denied";
    }
}
