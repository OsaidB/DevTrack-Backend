package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.ProjectDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.ProjectMapper;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.repository.ProjectRepo;
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

//    @Autowired
//    private ProjectMapper projectMapper;
    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = ProjectMapper.INSTANCE.toProjectEntity(projectDTO);
        Project savedProject = projectRepo.save(project);
        return ProjectMapper.INSTANCE.toProjectDTO(savedProject);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        return ProjectMapper.INSTANCE.toProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(ProjectMapper.INSTANCE::toProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setUpdatedAt(updatedProject.getUpdatedAt());

        Project updatedProjectEntity = projectRepo.save(project);
        return ProjectMapper.INSTANCE.toProjectDTO(updatedProjectEntity);
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
        projectRepo.delete(project);
    }
}
