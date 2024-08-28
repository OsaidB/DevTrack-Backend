package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepo extends JpaRepository<ProjectMember, Long> {
    List<ProjectMember> findByProjectProjectId(Long projectId);
    List<ProjectMember> findByUserId(Long userId);

    boolean existsByProjectProjectIdAndUserId(Long projectId, Long userId);


}
