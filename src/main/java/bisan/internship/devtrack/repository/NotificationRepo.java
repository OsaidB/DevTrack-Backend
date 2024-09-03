package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientId(Long recipientId); // Find notifications by recipient ID
    List<Notification> findBySenderId(Long senderId); // Find notifications by sender ID
}
