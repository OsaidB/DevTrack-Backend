package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.ProjectMember;
import bisan.internship.devtrack.model.entity.User;

public class ProjectMemberMapper {

    public static ProjectMemberDTO mapToProjectMemberDTO(ProjectMember projectMember) {
        return new ProjectMemberDTO(
                projectMember.getProjectMemberId(),
                projectMember.getProject().getProjectId(),
                projectMember.getUser().getUserId(),
                projectMember.getRole(),
                projectMember.getCreatedAt(),
                projectMember.getUpdatedAt()
        );
    }

    public static ProjectMember mapToProjectMemberEntity(ProjectMemberDTO projectMemberDTO, Project project, User user) {
        return new ProjectMember(
                projectMemberDTO.getProjectMemberId(),
                project,
                user,
                projectMemberDTO.getRole(),
                projectMemberDTO.getCreatedAt(),
                projectMemberDTO.getUpdatedAt()
        );
    }
}
