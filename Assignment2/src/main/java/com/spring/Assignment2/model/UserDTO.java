package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.Role;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserDTO {
    private int id;

    private String address;

    private String description;

    private String email;

    private String fullName;

    private String image;

    private String password;

    private String phoneNumber;

    private int status;

    private String checkPass;

    private Role role;

    private CV cv;

    private Company company;

    private MultipartFile file;

    public UserDTO() {
    }

    public UserDTO(String address, String description, String email, String fullName,
                   String image, String password, String phoneNumber,String checkPass,
                   int status, Role role, CV cv) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.fullName = fullName;
        this.image = image;
        this.password = password;
        this.checkPass = checkPass;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.role = role;
        this.cv = cv;
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

    public String getDescription() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CV getCv() {
        return cv;
    }

    public void setCv(CV cv) {
        this.cv = cv;
    }

    public String getCheckPass() {
        return checkPass;
    }

    public void setCheckPass(String checkPass) {
        this.checkPass = checkPass;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
