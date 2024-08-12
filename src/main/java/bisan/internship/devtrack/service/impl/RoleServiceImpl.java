package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.mapper.RoleMapper;
import bisan.internship.devtrack.model.entity.Role;
import bisan.internship.devtrack.repository.RoleRepo;
import bisan.internship.devtrack.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        Role savedRole = roleRepo.save(role);
        return RoleMapper.INSTANCE.toRoleDTO(savedRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles.stream().map(RoleMapper.INSTANCE::toRoleDTO).collect(Collectors.toList());
    }
}
