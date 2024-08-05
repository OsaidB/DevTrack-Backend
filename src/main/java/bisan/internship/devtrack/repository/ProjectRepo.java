package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {

}
