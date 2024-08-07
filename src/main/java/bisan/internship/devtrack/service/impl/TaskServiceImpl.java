package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.TaskMapper;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.TaskRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Project project = projectRepo.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + taskDTO.getProjectId()));

        // Retrieve project members
        List<User> projectMembers = userRepo.findByProjectId(taskDTO.getProjectId());

        // Check if assigned user is a member of the project
        User assignedTo = null;
        if (taskDTO.getAssignedToUserId() != null) {
            assignedTo = projectMembers.stream()
                    .filter(user -> user.getUserId().equals(taskDTO.getAssignedToUserId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user is not a member of the project"));
        }

        Task task = TaskMapper.mapToTaskEntity(taskDTO, project, assignedTo);
        Task savedTask = taskRepo.save(task);

        return TaskMapper.mapToTaskDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        return TaskMapper.mapToTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(TaskMapper::mapToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        task.setTaskName(updatedTaskDTO.getTaskName());
        task.setTaskDescription(updatedTaskDTO.getTaskDescription());
        task.setStatus(updatedTaskDTO.getStatus());
        task.setPriority(updatedTaskDTO.getPriority());
        task.setUpdatedAt(updatedTaskDTO.getUpdatedAt());

        // Retrieve project members
        List<User> projectMembers = userRepo.findByProjectId(updatedTaskDTO.getProjectId());

        // Check if assigned user is a member of the project
        User assignedTo = null;
        if (updatedTaskDTO.getAssignedToUserId() != null) {
            assignedTo = projectMembers.stream()
                    .filter(user -> user.getUserId().equals(updatedTaskDTO.getAssignedToUserId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user is not a member of the project"));
        }
        task.setAssignedTo(assignedTo);

        Task updatedTask = taskRepo.save(task);
        return TaskMapper.mapToTaskDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));
        taskRepo.delete(task);
    }
    @Override
    public List<TaskDTO> getTasksByProjectId(long projectId) {
        List<Task> tasks = taskRepo.findByProjectProjectId(projectId);
        return tasks.stream()
                .map(TaskMapper::mapToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserId(long userId) {
        List<Task> tasks = taskRepo.findByAssignedToUserId(userId);
        return tasks.stream()
                .map(TaskMapper::mapToTaskDTO)
                .collect(Collectors.toList());
    }
}
