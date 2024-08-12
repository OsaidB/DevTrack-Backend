package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.ProjectMember;
import bisan.internship.devtrack.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    ProjectMemberMapper INSTANCE = Mappers.getMapper(ProjectMemberMapper.class);


    @Mappings({
            @Mapping(source = "project.projectId", target = "projectId"),
            @Mapping(source = "user.userId", target = "userId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)

    })
    ProjectMemberDTO toProjectMemberDTO(ProjectMember projectMember);

    @Mappings({
            @Mapping(source = "projectId", target = "project.projectId"),
            @Mapping(source = "userId", target = "user.userId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)

    })
    ProjectMember toProjectMemberEntity(ProjectMemberDTO projectMemberDTO);
}
