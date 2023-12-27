package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.ApplyPost;
import com.spring.Assignment2.entity.Category;
import com.spring.Assignment2.entity.Company;
import jakarta.persistence.*;

import java.util.List;


public class RecruitmentDTO{
    private int id;

    private String address;

    private String createdAt;

    private String description;

    private String experience;

    private int quantity;

    private String rankRe;

    private String salary;

    private int status;

    private String title;

    private String type;

    private int view;

    private String deadline;

    private Category category;

    private Company company;

    private List<ApplyPost> applyPosts;

    private String keySearch;

    public RecruitmentDTO() {
    }

    public RecruitmentDTO(String address, String createdAt, String description, String experience,
                          int quantity, String rankRe, String salary, int status, String title,
                          String type, int view, String deadline, Category category, Company company,List<ApplyPost> applyPosts) {
        this.address = address;
        this.createdAt = createdAt;
        this.description = description;
        this.experience = experience;
        this.quantity = quantity;
        this.rankRe = rankRe;
        this.salary = salary;
        this.status = status;
        this.title = title;
        this.type = type;
        this.view = view;
        this.deadline = deadline;
        this.category = category;
        this.company = company;
        this.applyPosts = applyPosts;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRankRe() {
        return rankRe;
    }

    public void setRankRe(String rankRe) {
        this.rankRe = rankRe;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<ApplyPost> getApplyPosts() {
        return applyPosts;
    }

    public void setApplyPosts(List<ApplyPost> applyPosts) {
        this.applyPosts = applyPosts;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

}
