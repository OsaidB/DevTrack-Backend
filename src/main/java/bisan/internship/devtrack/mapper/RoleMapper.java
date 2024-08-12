package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.model.entity.Role;
import org.mapstruct.factory.Mappers;


public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


    RoleDTO toRoleDTO(Role role);
    Role toRoleEntity(RoleDTO roleDTO);
}
