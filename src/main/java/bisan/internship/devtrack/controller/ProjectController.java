package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.service.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid  @RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("projectId") long projectId) {
        ProjectDTO projectDTO = projectService.getProjectById(projectId);
        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PutMapping("{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("projectId") long projectId,
                                                    @Valid @RequestBody ProjectDTO updatedProject) {
        ProjectDTO projectDTO = projectService.updateProject(projectId, updatedProject);
        return ResponseEntity.ok(projectDTO);
    }

    @DeleteMapping("{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable("projectId") long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project deleted successfully");
    }
}
