package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.NotificationDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.NotificationMapper;
import bisan.internship.devtrack.model.entity.Notification;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.NotificationRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        User user = userRepo.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + notificationDTO.getNotificationId()));

        Notification notification = NotificationMapper.mapToNotificationEntity(notificationDTO, user);
        Notification savedNotification = notificationRepo.save(notification);

        return NotificationMapper.mapToNotificationDto(savedNotification);
    }

    @Override
    public NotificationDTO getNotificationById(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        return NotificationMapper.mapToNotificationDto(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications(){
        List<Notification> notifications = notificationRepo.findAll();
        return notifications.stream().map(NotificationMapper::mapToNotificationDto).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO updateNotification(Long notificationId, NotificationDTO updatedNotification){
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        User user = userRepo.findById(updatedNotification.getNotificationId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedNotification.getNotificationId()));

        notification.setUserId(user);
        notification.setIsRead(updatedNotification.getIsRead());
        notification.setMessage(updatedNotification.getMessage());
        notification.setCreatedAt(updatedNotification.getCreatedAt());

        Notification saveNotification = notificationRepo.save(notification);
        return NotificationMapper.mapToNotificationDto(saveNotification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        notificationRepo.delete(notification);
    }
}
