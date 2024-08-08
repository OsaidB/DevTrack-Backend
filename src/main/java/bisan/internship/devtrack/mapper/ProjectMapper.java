package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.model.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO toProjectDTO(Project project);

    Project toProjectEntity(ProjectDTO projectDTO);
}
