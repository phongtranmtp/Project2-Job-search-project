package com.spring.Assignment2.service;

import com.spring.Assignment2.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

    void add(UserDTO userDTO);

    boolean checkPass(String email, String password);

    boolean checkEmail(String email);

    void updateProfile(UserDTO userDTO);

    void updateImage(UserDTO userDTO);

    void confirmAccount(UserDTO userDTO);
}
