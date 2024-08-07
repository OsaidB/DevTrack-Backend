package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mappings({
            @Mapping(source = "project.projectId", target = "projectId"),
            @Mapping(source = "assignedTo.userId", target = "assignedToUserId", nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.SET_TO_NULL)
    })
    TaskDTO toTaskDTO(Task task);

    @Mappings({
            @Mapping(source = "projectId", target = "project.projectId"),
            @Mapping(source = "assignedToUserId", target = "assignedTo.userId")
    })
    Task toTaskEntity(TaskDTO taskDTO, Project project, User assignedTo);
}
