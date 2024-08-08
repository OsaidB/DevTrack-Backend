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

    ProjectMemberDTO toProjectMemberDTO(ProjectMember projectMember);

    ProjectMember toProjectMemberEntity(ProjectMemberDTO projectMemberDTO);
}
