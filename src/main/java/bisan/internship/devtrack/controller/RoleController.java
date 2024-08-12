package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.model.entity.Role;
import bisan.internship.devtrack.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("{roleId}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable("roleId") long roleId) {
        RoleDTO roleDTO = roleService.getRoleById(roleId);
        return ResponseEntity.ok(roleDTO);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRole(@PathVariable("roleId") long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
