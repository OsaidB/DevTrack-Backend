package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.TaskMapper;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.BoardRepo;  // Assuming you have this repository
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

    @Autowired
    private BoardRepo boardRepo;  // Add BoardRepo to access boards

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        // Check if the project exists
        Project project = projectRepo.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + taskDTO.getProjectId()));

        // Check if the board exists
        Board board = boardRepo.findById(taskDTO.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + taskDTO.getBoardId()));

        // Retrieve project members
        List<User> projectMembers = userRepo.findByProjectId(taskDTO.getProjectId());

        // Initialize assigned user as null
        User assignedTo = null;

        // Check if assigned user ID is provided and if the user is a member of the project
        if (taskDTO.getAssignedToUserId() != null) {
            assignedTo = projectMembers.stream()
                    .filter(user -> user.getUserId().equals(taskDTO.getAssignedToUserId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user is not a member of the project"));
        }

        // Map DTO to entity
        Task task = TaskMapper.INSTANCE.toTaskEntity(taskDTO);

        // Set the assigned user and board
        task.setAssignedTo(assignedTo);

        // Set additional fields if necessary
        task.setProject(project); // Ensure you set the project as well
        task.setBoard(board); // Set the board

        // Save the task
        Task savedTask = taskRepo.save(task);

        // Map entity back to DTO and return
        return TaskMapper.INSTANCE.toTaskDTO(savedTask);
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        return TaskMapper.INSTANCE.toTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }
    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO updatedTaskDTO) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if (!task.getProject().getProjectId().equals(updatedTaskDTO.getProjectId())) {
            throw new IllegalArgumentException("Changing the projectId is not allowed.");
        }
        // Update board
        if (!task.getBoard().getBoardId().equals(updatedTaskDTO.getBoardId())) {
            throw new IllegalArgumentException("Changing the boardId is not allowed.");
        }
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
        return TaskMapper.INSTANCE.toTaskDTO(updatedTask);
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
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByUserId(long userId) {
        List<Task> tasks = taskRepo.findByAssignedToUserId(userId);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getTasksByBoardId(long boardId) {
        List<Task> tasks = taskRepo.findByBoardBoardId(boardId);
        return tasks.stream()
                .map(TaskMapper.INSTANCE::toTaskDTO)
                .collect(Collectors.toList());
    }
}
