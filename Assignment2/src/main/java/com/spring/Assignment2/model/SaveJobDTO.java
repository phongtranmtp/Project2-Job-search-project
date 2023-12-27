package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.Recruitment;
import com.spring.Assignment2.entity.User;

public class SaveJobDTO {
    private int id;

    private User user;

    private Recruitment recruitment;

    public SaveJobDTO() {
    }

    public SaveJobDTO(int id, User user, Recruitment recruitment) {
        this.id = id;
        this.user = user;
        this.recruitment = recruitment;
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

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }
}
