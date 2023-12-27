package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.User;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

public class ApplyPostDTO {
    private int id;

    private String createdAt;

    private String nameCv;

    private int status;

    private String text;

    private User user;

    private Recruitment recruitment;

    private MultipartFile file;

    public ApplyPostDTO() {
    }

    public ApplyPostDTO(String createdAt, String nameCv, int status, String text, User user, Recruitment recruitment) {
        this.createdAt = createdAt;
        this.nameCv = nameCv;
        this.status = status;
        this.text = text;
        this.user = user;
        this.recruitment = recruitment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNameCv() {
        return nameCv;
    }

    public void setNameCv(String nameCv) {
        this.nameCv = nameCv;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
