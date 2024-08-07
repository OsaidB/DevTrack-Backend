package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
