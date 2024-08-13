package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO NotificationDTO);
    NotificationDTO getNotificationById(Long notificationId);
    List<NotificationDTO> getAllNotifications();
    List<NotificationDTO> getNotificationsByUserId(Long userId);
    NotificationDTO updateNotification(Long notificationId, NotificationDTO updatedNotification);
    void deleteNotification(Long notificationId);
}
