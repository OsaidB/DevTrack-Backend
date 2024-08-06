package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectMemberRepo extends JpaRepository<ProjectMember, Long> {
}
