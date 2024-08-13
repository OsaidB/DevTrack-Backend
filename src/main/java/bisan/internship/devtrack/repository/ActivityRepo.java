package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Activity;
import bisan.internship.devtrack.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
    List<Activity> getActivitiesByTaskTaskId(Long taskId);
}
