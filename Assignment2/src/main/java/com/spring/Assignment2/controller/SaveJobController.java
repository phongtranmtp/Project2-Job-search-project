package com.spring.Assignment2.controller;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.SaveJob;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.SaveJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaveJobController {
    @Autowired
    SaveJobService saveJobService;

    /* hiển thị danh sách công việc đã lưu và phân trang*/
    @GetMapping("/user/get-list-saveJob")
    private String getListSaveJob(Model model,@RequestParam(value = "page",defaultValue = "1") Integer page){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.getId());
        userDTO.setFullName(currentUser.getName());
        userDTO.setRole(currentUser.getRoleId());
        model.addAttribute("user",userDTO);

        Page<SaveJob> saveJobs = saveJobService.getSaveJobPages(currentUser.getId(),page);
        model.addAttribute("saveJobs",saveJobs);
        model.addAttribute("totalPages",saveJobs.getTotalPages());
        model.addAttribute("currentPage",page);

        return "public/list-save-job";
    }
}
