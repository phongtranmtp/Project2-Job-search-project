package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.User;
import jakarta.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

public class CompanyDTO {
    private int id;

    private String address;

    private String description;

    private String email;

    private String logo;

    private String nameCompany;

    private String phoneNumber;

    private int status;

    private int quantityApply;

    private User user;

    private List<Recruitment> recruitments;

    private MultipartFile file;

    public CompanyDTO() {
    }

    public CompanyDTO(String address, String description, String email, String logo, String nameCompany,
                      String phoneNumber, int status,int quantityApply, User user, List<Recruitment> recruitments) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.logo = logo;
        this.nameCompany = nameCompany;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.quantityApply = quantityApply;
        this.user = user;
        this.recruitments = recruitments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String  getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recruitment> getRecruitments() {
        return recruitments;
    }

    public void setRecruitments(List<Recruitment> recruitments) {
        this.recruitments = recruitments;
    }

    public int getQuantityApply() {
        return quantityApply;
    }

    public void setQuantityApply(int quantityApply) {
        this.quantityApply = quantityApply;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
