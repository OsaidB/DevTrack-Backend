package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDto);
    ProjectDTO getProjectById(Long projectId);
    List<ProjectDTO> getAllProjects();
    ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject);
    void deleteProject(Long projectId);
}
