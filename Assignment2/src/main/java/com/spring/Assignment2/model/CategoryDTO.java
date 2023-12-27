package com.spring.Assignment2.model;

import com.spring.Assignment2.entity.Recruitment;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDTO {

    private int id;

    private String name;

    private int numberChoose;

    private List<Recruitment> recruitments;

    public CategoryDTO() {
    }

    public CategoryDTO(String name, int numberChoose) {
        this.name = name;
        this.numberChoose = numberChoose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberChoose() {
        return numberChoose;
    }

    public void setNumberChoose(int numberChoose) {
        this.numberChoose = numberChoose;
    }

    public List<Recruitment> getRecruitments() {
        return recruitments;
    }

    public void setRecruitments(List<Recruitment> recruitments) {
        this.recruitments = recruitments;
    }
}
