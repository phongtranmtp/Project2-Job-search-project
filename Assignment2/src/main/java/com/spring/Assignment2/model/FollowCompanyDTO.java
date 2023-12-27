package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.Company;
import com.spring.Assignment2.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class FollowCompanyDTO {
    private int id;

    private User user;

    private Company company;

    public FollowCompanyDTO() {
    }

    public FollowCompanyDTO(int id, User user, Company company) {
        this.id = id;
        this.user = user;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
