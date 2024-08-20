package bisan.internship.devtrack.service.impl;

//import bisan.internship.devtrack.service.RoleService;
import bisan.internship.devtrack.service.RoleService;
        import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
//    @Autowired
//    private RoleRepo roleRepo;
//
//    @Autowired
//    private RoleConfigurationService roleConfigurationService;
//
//    @Override
//    public RoleDTO createRole(RoleDTO roleDTO) {
//        Role role = RoleMapper.INSTANCE.toRoleEntity(roleDTO);
//        Role savedRole = roleRepo.save(role);
//
//        // Update the RoleConfigurationService with the new ID
//        updateRoleConstants(roleDTO.getRoleName(), savedRole.getRoleId());
//
//        return RoleMapper.INSTANCE.toRoleDTO(savedRole);
//    }
//
//    // New method to get or create a role by name
//    public RoleDTO getOrCreateRole(String roleName) {
//        List<RoleDTO> roles = getAllRoles();
//        return roles.stream()
//                .filter(role -> roleName.equalsIgnoreCase(role.getRoleName()))
//                .findFirst()
//                .orElseGet(() -> createRole(new RoleDTO(null, roleName)));
//    }
//
//    @Override
//    public RoleDTO getRoleById(Long roleId) {
//        Role role = roleRepo.findById(roleId)
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found" + roleId));
//        return RoleMapper.INSTANCE.toRoleDTO(role);
//    }
//
//    @Override
//    public List<RoleDTO> getAllRoles() {
//        List<Role> roles = roleRepo.findAll();
//        return roles.stream().map(RoleMapper.INSTANCE::toRoleDTO).collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteRole(Long roleId) {
//        Role role = roleRepo.findById(roleId)
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found" + roleId));
//        roleRepo.delete(role);
//    }
//    public boolean isRoleName(Long roleId, String expectedRoleName) {
//        RoleDTO roleDTO = getRoleById(roleId);
//        return expectedRoleName.equals(roleDTO.getRoleName());
//    }
//
//
//    private void updateRoleConstants(String roleName, Long roleId) {
//        switch (roleName.toLowerCase()) {
//            case "backend":
//                roleConfigurationService.setBackendRoleId(roleId);
//                break;
//            case "frontend":
//                roleConfigurationService.setFrontendRoleId(roleId);
//                break;
//            case "qa":
//                roleConfigurationService.setQaRoleId(roleId);
//                break;
//        }
//    }

}
