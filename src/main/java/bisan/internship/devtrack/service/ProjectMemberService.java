package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.ProjectMemberDTO;

import java.util.List;

public interface ProjectMemberService {
    ProjectMemberDTO createProjectMember(ProjectMemberDTO projectMemberDTO);
    ProjectMemberDTO getProjectMemberById(Long projectMemberId);
    List<ProjectMemberDTO> getAllProjectMembers();
    ProjectMemberDTO updateProjectMember(Long projectMemberId, ProjectMemberDTO updatedProjectMember);
    void deleteProjectMember(Long projectMemberId);
}
