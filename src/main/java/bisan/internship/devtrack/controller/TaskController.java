package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.TaskDTO;
import bisan.internship.devtrack.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") long taskId) {
        TaskDTO taskDTO = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskDTO);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") long taskId,
                                              @RequestBody TaskDTO updatedTaskDTO) {
        TaskDTO taskDTO = taskService.updateTask(taskId, updatedTaskDTO);
        return ResponseEntity.ok(taskDTO);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
