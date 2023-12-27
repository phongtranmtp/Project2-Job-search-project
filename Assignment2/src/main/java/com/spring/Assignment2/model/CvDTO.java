package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.User;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

public class CvDTO {

    private int id;

    private String fileName;

    private MultipartFile file;

    private User user;

    public CvDTO() {
    }

    public CvDTO(String fileName, User user) {
        this.fileName = fileName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
