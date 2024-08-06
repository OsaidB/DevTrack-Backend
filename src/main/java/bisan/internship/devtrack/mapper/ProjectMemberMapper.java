package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.ProjectMember;
import bisan.internship.devtrack.model.entity.User;

public class ProjectMemberMapper {

    public static ProjectMemberDTO mapToProjectMemberDTO(ProjectMember projectMember) {
        if (projectMember == null) {
            return null;
        }

        return new ProjectMemberDTO(
                projectMember.getProjectMemberId(),
                projectMember.getProject() != null ? projectMember.getProject().getProjectId() : null,
                projectMember.getUser() != null ? projectMember.getUser().getUserId() : null,
                projectMember.getJoinedAt()
        );
    }

    public static ProjectMember mapToProjectMemberEntity(ProjectMemberDTO projectMemberDTO, Project project, User user) {
        if (projectMemberDTO == null || project == null || user == null) {
            return null;
        }

        return new ProjectMember(
                projectMemberDTO.getProjectMemberId(),
                project,
                user,
                projectMemberDTO.getJoinedAt()
        );
    }
}
