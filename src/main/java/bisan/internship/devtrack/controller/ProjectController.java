package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Build Add Project REST API
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDto){
        ProjectDTO createdProject = projectService.createProject(projectDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Build Get Project REST API
    @GetMapping("{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") long projectId){
        ProjectDTO projectDto = projectService.getProjectById(projectId);
        return ResponseEntity.ok(projectDto);
    }

    // Build Get All Projects REST API
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects(){
        List<ProjectDTO> projectsDTO = projectService.getAllProjects();
        return ResponseEntity.ok(projectsDTO);
    }

    // Build Update Project REST API
    @PutMapping("{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("id") long projectId,
                                                    @RequestBody ProjectDTO updatedProject){
        ProjectDTO projectDto = projectService.updateProject(projectId, updatedProject);
        return ResponseEntity.ok(projectDto);
    }

    // Build Delete Project REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") long projectId){
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project Deleted Successfully");
    }

}
