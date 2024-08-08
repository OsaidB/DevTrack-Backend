package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepo extends JpaRepository<Activity, Long> {
}
