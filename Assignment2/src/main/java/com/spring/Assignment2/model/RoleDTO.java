package com.spring.Assignment2.model;

public class RoleDTO {

    private int id;

    private String roleName;

    public RoleDTO() {
    }

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
