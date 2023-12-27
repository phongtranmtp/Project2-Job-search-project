package com.spring.Assignment2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "follow_company")
public class FollowCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public FollowCompany() {
    }

    public FollowCompany(User user, Company company) {
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

    public Company getCompanies() {
        return company;
    }

    public void setCompanies(Company company) {
        this.company = company;
    }
}
