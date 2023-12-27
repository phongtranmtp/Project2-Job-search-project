package com.spring.Assignment2.service.impl;

import com.spring.Assignment2.dao.RoleRepository;
import com.spring.Assignment2.entity.Role;
import com.spring.Assignment2.model.RoleDTO;
import com.spring.Assignment2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(convertRoleDTO(role));
        }
        return roleDTOs;
    }

    public RoleDTO convertRoleDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getRoleName());

        return roleDTO;
    }
}
