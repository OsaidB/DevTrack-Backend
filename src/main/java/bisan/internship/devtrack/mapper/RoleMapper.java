package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.RoleDTO;
import bisan.internship.devtrack.model.entity.FunctionalRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toRoleDTO(FunctionalRole role);
    FunctionalRole toRoleEntity(RoleDTO roleDTO);
}
