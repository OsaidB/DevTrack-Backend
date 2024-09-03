package bisan.internship.devtrack.service;

import bisan.internship.devtrack.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO notificationDTO);
    NotificationDTO getNotificationById(Long notificationId);
    List<NotificationDTO> getAllNotifications();
    List<NotificationDTO> getNotificationsByRecipientId(Long recipientId); // Updated method name
    List<NotificationDTO> getNotificationsBySenderId(Long senderId); // New method for sender
    NotificationDTO updateNotification(Long notificationId, NotificationDTO updatedNotification);
    void deleteNotification(Long notificationId);
}
