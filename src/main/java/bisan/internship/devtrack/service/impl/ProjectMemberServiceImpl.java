package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.ProjectMemberMapper;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.ProjectMember;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.ProjectMemberRepo;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.ProjectMemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {

    @Autowired
    private ProjectMemberRepo projectMemberRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ProjectMemberDTO addProjectMember(ProjectMemberDTO projectMemberDTO) {
        Project project = projectRepo.findById(projectMemberDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectMemberDTO.getProjectId()));

        User user = userRepo.findById(projectMemberDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectMemberDTO.getUserId()));

        // Check if the user is already a member of the project
        boolean exists = projectMemberRepo.existsByProjectProjectIdAndUserUserId(projectMemberDTO.getProjectId(), projectMemberDTO.getUserId());
        if (exists) {
            throw new IllegalArgumentException("User is already a member of the project.");
        }

        ProjectMember projectMember = ProjectMemberMapper.mapToProjectMemberEntity(projectMemberDTO, project, user);
        ProjectMember savedProjectMember = projectMemberRepo.save(projectMember);

        return ProjectMemberMapper.mapToProjectMemberDTO(savedProjectMember);
    }

    @Override
    public ProjectMemberDTO getProjectMemberById(Long projectMemberId) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));

        return ProjectMemberMapper.mapToProjectMemberDTO(projectMember);
    }

    @Override
    public List<ProjectMemberDTO> getAllProjectMembers() {
        List<ProjectMember> projectMembers = projectMemberRepo.findAll();
        return projectMembers.stream()
                .map(ProjectMemberMapper::mapToProjectMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectMemberDTO updateProjectMember(Long projectMemberId, ProjectMemberDTO updatedProjectMemberDTO) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));

        // No role field anymore, update only joinedAt if needed
        projectMember.setJoinedAt(updatedProjectMemberDTO.getJoinedAt());

        ProjectMember updatedProjectMember = projectMemberRepo.save(projectMember);
        return ProjectMemberMapper.mapToProjectMemberDTO(updatedProjectMember);
    }

    @Override
    public void deleteProjectMember(Long projectMemberId) {
        ProjectMember projectMember = projectMemberRepo.findById(projectMemberId)
                .orElseThrow(() -> new ResourceNotFoundException("Project member not found with id: " + projectMemberId));
        projectMemberRepo.delete(projectMember);
    }

    @Override
    public List<ProjectMemberDTO> getMembersByProjectId(Long projectId) {
        List<ProjectMember> projectMembers = projectMemberRepo.findByProjectProjectId(projectId);
        return projectMembers.stream()
                .map(ProjectMemberMapper::mapToProjectMemberDTO)
                .collect(Collectors.toList());
    }
}
