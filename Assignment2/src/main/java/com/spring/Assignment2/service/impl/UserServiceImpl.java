package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.CVRepository;
import com.spring.Assignment2.dao.UserRepository;
import com.spring.Assignment2.entity.CV;
import com.spring.Assignment2.entity.User;
import com.spring.Assignment2.model.UserDTO;
import com.spring.Assignment2.model.UserPrincipal;
import com.spring.Assignment2.security.PasswordGenerator;
import com.spring.Assignment2.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CVRepository cvRepository;

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user: users) {
            userDTOs.add(convertUserDTO(user));
        }
        return userDTOs;
    }

    /* Thêm mới user */
    @Override
    public void add(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setAddress(userDTO.getAddress());
        user.setDescription(userDTO.getDescription());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setImage(userDTO.getImage());
        user.setPassword(PasswordGenerator.getHashString(userDTO.getPassword()));
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(userDTO.getRole());
        if (user.getRole().getId()==1){
            user.setStatus(1);
        } else {
            user.setStatus(userDTO.getStatus());
        }

        user.setCv(userDTO.getCv());
        userRepository.save(user);
    }

    /* Kiểm tra mật khẩu */
    @Override
    public boolean checkPass(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    /* Kiểm tra email */
    @Override
    public boolean checkEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            return false;
        }
        return true;
    }

    /* Cập nhập thông tin user */
    @Override
    public void updateProfile(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null){
            user.setAddress(userDTO.getAddress());
            user.setDescription(userDTO.getDescription());
            user.setEmail(userDTO.getEmail());
            user.setFullName(userDTO.getFullName());
            user.setPhoneNumber(userDTO.getPhoneNumber());

            userRepository.save(user);
        }
    }

    /* cập nhập ảnh cho user */
    @Override
    public void updateImage(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null){
            user.setImage(userDTO.getImage());
            userRepository.save(user);
        }
    }

    /* xác nhậm mail */
    @Override
    public void confirmAccount(UserDTO userDTO) {
        User user = userRepository.findUserByEmail(userDTO.getEmail());
        if (user != null){
            user.setStatus(userDTO.getStatus());
            userRepository.save(user);
        }
    }

    public UserDTO convertUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAddress(user.getAddress());
        userDTO.setDescription(user.getDescription());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setImage(user.getImage());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setStatus(user.getStatus());
//        userDTO.setCompany(user.getCompany());
        userDTO.setRole(user.getRole());
        userDTO.setCv(user.getCv());
        return userDTO;
    }

    /* Lấy ra user đã đang nhập */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email); // user của userdetail

        if (user == null) {
            throw new UsernameNotFoundException("not found");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));

        UserPrincipal accountDTO = new UserPrincipal(user.getEmail(), user.getPassword(),true ,true, true, true,authorities);

        accountDTO.setId(user.getId());
        accountDTO.setName(user.getFullName());

        accountDTO.setRoleId(user.getRole());
        accountDTO.setEmail(user.getEmail());
        accountDTO.setAddress(user.getAddress());
        accountDTO.setPhoneNumber(user.getPhoneNumber());
        accountDTO.setDescription(user.getDescription());
        accountDTO.setStatus(user.getStatus());
        accountDTO.setImage(user.getImage());
        accountDTO.setCompany(user.getCompany());
        accountDTO.setCv(user.getCv());
        return accountDTO;
    }
}
