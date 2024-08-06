package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;

public class TaskMapper {

    public static TaskDTO mapToTaskDTO(Task task) {
        return new TaskDTO(
                task.getTaskId(),
                task.getProject().getProjectId(),
                task.getAssignedTo() != null ? task.getAssignedTo().getUserId() : null,
                task.getTaskName(),
                task.getTaskDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }

    public static Task mapToTaskEntity(TaskDTO taskDTO, Project project, User assignedTo) {
        return new Task(
                taskDTO.getTaskId(),
                project,
                assignedTo,
                taskDTO.getTaskName(),
                taskDTO.getTaskDescription(),
                taskDTO.getStatus(),
                taskDTO.getPriority(),
                taskDTO.getCreatedAt(),
                taskDTO.getUpdatedAt()
        );
    }
}
