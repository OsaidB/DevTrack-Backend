package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.NotificationDTO;
import bisan.internship.devtrack.model.entity.Notification;
import bisan.internship.devtrack.model.entity.User;

public class NotificationMapper {
    public static NotificationDTO mapToNotificationDto(Notification notification) {
        return new NotificationDTO(
                notification.getNotificationId(),
                notification.getUserId().getUserId(),
                notification.getMessage(),
                notification.getIsRead(),
                notification.getCreatedAt()
        );
    }
    public static Notification mapToNotificationEntity(NotificationDTO notificationDTO, User user) {
        return new Notification(
                notificationDTO.getNotificationId(),
                user,
                notificationDTO.getMessage(),
                notificationDTO.getIsRead(),
                notificationDTO.getCreatedAt()
        );
    }
}
