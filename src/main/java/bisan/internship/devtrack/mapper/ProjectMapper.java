package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.User;

public class ProjectMapper {

    public static ProjectDTO mapToProjectDTO(Project project) { // method to map (convert) Project JPA entity into ProjectDTO
        return new ProjectDTO(
                project.getProjectId(),
                project.getName(),
                project.getDescription(),
                project.getCreatedBy().getUserId(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    public static Project mapToProjectEntity(ProjectDTO projectDto, User createdBy) { // method to map (convert) ProjectDTO into Project JPA entity
        return new Project(
                projectDto.getProjectId(),
                projectDto.getName(),
                projectDto.getDescription(),
                createdBy,
                projectDto.getCreatedAt(),
                projectDto.getUpdatedAt()
        );
    }
}
