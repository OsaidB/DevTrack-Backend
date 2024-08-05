package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.ProjectMapper;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDto) {
        User createdBy = userRepo.findById(projectDto.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectDto.getCreatedBy()));

        Project project = ProjectMapper.mapToProjectEntity(projectDto, createdBy);
        Project savedProject = projectRepo.save(project);

        return ProjectMapper.mapToProjectDTO(savedProject);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not exists with given id: " + projectId));

        return ProjectMapper.mapToProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream().map(ProjectMapper::mapToProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject) {
        Project project = projectRepo.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("Project not exists with given id: " + projectId)
        );

        User createdBy = userRepo.findById(updatedProject.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedProject.getCreatedBy()));

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setCreatedBy(createdBy);
        project.setCreatedAt(updatedProject.getCreatedAt());
        project.setUpdatedAt(updatedProject.getUpdatedAt());

        Project updatedProjectObj = projectRepo.save(project);
        return ProjectMapper.mapToProjectDTO(updatedProjectObj);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(
                () -> new ResourceNotFoundException("Project not exists with given id: " + projectId)
        );

        projectRepo.deleteById(projectId);
    }
}
