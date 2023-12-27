package com.spring.Assignment2.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "applypost")
public class ApplyPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "name_cv")
    private String nameCv;

    @Column(name = "status")
    private int status;

    @Column(name = "text")
    private String text;

    @Transient // field is not persistent.
    private MultipartFile file;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    public ApplyPost() {
    }

    public ApplyPost(String createdAt, String nameCv, int status,
                     String text, User user, Recruitment recruitment) {
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
