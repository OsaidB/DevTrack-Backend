package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO getRoleById(Long roleId);
    List<RoleDTO> getAllRoles();
    void deleteRole(Long roleId);
}
