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
//                project.getCreatedBy().getUserId(), //always created by admin
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }

    public static Project mapToProjectEntity(ProjectDTO projectDto) { // method to map (convert) ProjectDTO into Project JPA entity
        return new Project(
                projectDto.getProjectId(),
                projectDto.getName(),
                projectDto.getDescription(),
//                createdBy, //always created by admin
                projectDto.getCreatedAt(),
                projectDto.getUpdatedAt()
        );
    }
}
