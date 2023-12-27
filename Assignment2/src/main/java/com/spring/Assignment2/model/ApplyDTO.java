package com.spring.Assignment2.model;

import org.springframework.web.multipart.MultipartFile;

public class ApplyDTO {
    private int idRe;

    private String text;

    private MultipartFile file;

    public ApplyDTO() {
    }

    public ApplyDTO(int idRe, String text, MultipartFile file) {
        this.idRe = idRe;
        this.text = text;
        this.file = file;
    }

    public int getIdRe() {
        return idRe;
    }

    public void setIdRe(int idRe) {
        this.idRe = idRe;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}


