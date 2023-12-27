package com.spring.Assignment2.controller;

import com.spring.Assignment2.dao.CVRepository;
import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.service.CvService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class CvController {

    @Autowired
    CvService cvService;

    @Autowired
    CVRepository cvRepository;

    /* Xóa CV */
    @PostMapping("/user/deleteCv")
    public String deleteCv(Integer id){
        UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        CV cv = cvRepository.getCvByUserId(currentUser.getId());
        cvService.deleteCvById(cv.getId());
        return "redirect:/home";
    }

    /* download CV của user để xem nội dụng CV */
    @GetMapping("/resources/uploads/{fileName}")
    public void downloadCv(@PathVariable String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String path =  "D:/downloadcv/";
        Path file = Paths.get(path + fileName);
        if (Files.exists(file)){
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition","attachment; filename=" + fileName);
            try {
                Files.copy(file,response.getOutputStream());
                response.getOutputStream().flush();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
