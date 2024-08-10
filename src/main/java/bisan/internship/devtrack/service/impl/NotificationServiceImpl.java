package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.NotificationDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.BoardMapper;
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

    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        User user = userRepo.findById(notificationDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + notificationDTO.getNotificationId()));

        Notification notification = notificationMapper.toNotificationEntity(notificationDTO);
        Notification savedNotification = notificationRepo.save(notification);

        return notificationMapper.  toNotificationDTO(savedNotification);
    }

    @Override
    public NotificationDTO getNotificationById(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        return notificationMapper.toNotificationDTO(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications(){
        List<Notification> notifications = notificationRepo.findAll();
        return notifications.stream().map(notificationMapper::toNotificationDTO).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO updateNotification(Long notificationId, NotificationDTO updatedNotification){
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        User user = userRepo.findById(updatedNotification.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedNotification.getNotificationId()));

        notification.setUserId(user);
        notification.setIsRead(updatedNotification.getIsRead());
        notification.setMessage(updatedNotification.getMessage());
//        notification.setCreatedAt(updatedNotification.getCreatedAt());

        Notification saveNotification = notificationRepo.save(notification);
        return notificationMapper.toNotificationDTO(saveNotification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        notificationRepo.delete(notification);
    }
}
