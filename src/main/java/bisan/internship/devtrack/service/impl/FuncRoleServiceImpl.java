package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.RoleMapper;
import bisan.internship.devtrack.model.entity.FunctionalRole;
import bisan.internship.devtrack.repository.FuncRoleRepo;
import bisan.internship.devtrack.service.FuncRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncRoleServiceImpl implements FuncRoleService {
    @Autowired
    private FuncRoleRepo funcRoleRepo;

    @Autowired
    private RoleConfigurationService roleConfigurationService;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        FunctionalRole role = RoleMapper.INSTANCE.toRoleEntity(roleDTO);
        FunctionalRole savedRole = funcRoleRepo.save(role);

        // Update the RoleConfigurationService with the new ID
        updateRoleConstants(roleDTO.getRoleName(), savedRole.getFuncRoleId());

        return RoleMapper.INSTANCE.toRoleDTO(savedRole);
    }

    // New method to get or create a role by name
    public RoleDTO getOrCreateRole(String roleName) {
        List<RoleDTO> roles = getAllRoles();
        return roles.stream()
                .filter(role -> roleName.equalsIgnoreCase(role.getRoleName()))
                .findFirst()
                .orElseGet(() -> createRole(new RoleDTO(null, roleName)));
    }

    @Override
    public RoleDTO getRoleById(Long roleId) {
        FunctionalRole role = funcRoleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found" + roleId));
        return RoleMapper.INSTANCE.toRoleDTO(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<FunctionalRole> roles = funcRoleRepo.findAll();
        return roles.stream().map(RoleMapper.INSTANCE::toRoleDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long roleId) {
        FunctionalRole role = funcRoleRepo.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found" + roleId));
        funcRoleRepo.delete(role);
    }
    public boolean isRoleName(Long roleId, String expectedRoleName) {
        RoleDTO roleDTO = getRoleById(roleId);
        return expectedRoleName.equals(roleDTO.getRoleName());
    }


    private void updateRoleConstants(String roleName, Long roleId) {
        switch (roleName.toLowerCase()) {
            case "backend":
                roleConfigurationService.setBackendRoleId(roleId);
                break;
            case "frontend":
                roleConfigurationService.setFrontendRoleId(roleId);
                break;
            case "qa":
                roleConfigurationService.setQaRoleId(roleId);
                break;
        }
    }

}
