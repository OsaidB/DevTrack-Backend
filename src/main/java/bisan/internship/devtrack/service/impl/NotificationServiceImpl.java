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
        User sender = userRepo.findById(notificationDTO.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found with id: " + notificationDTO.getSenderId()));

        User recipient = userRepo.findById(notificationDTO.getRecipientId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with id: " + notificationDTO.getRecipientId()));

        Notification notification = NotificationMapper.INSTANCE.toNotificationEntity(notificationDTO);
        notification.setSender(sender); // Set the sender
        notification.setRecipient(recipient); // Set the recipient

        Notification savedNotification = notificationRepo.save(notification);

        return NotificationMapper.INSTANCE.toNotificationDTO(savedNotification);
    }

    @Override
    public NotificationDTO getNotificationById(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        return NotificationMapper.INSTANCE.toNotificationDTO(notification);
    }

    @Override
    public List<NotificationDTO> getAllNotifications(){
        List<Notification> notifications = notificationRepo.findAll();
        return notifications.stream().map(NotificationMapper.INSTANCE::toNotificationDTO).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsByRecipientId(Long recipientId) {
        userRepo.findById(recipientId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + recipientId));

        List<Notification> notifications = notificationRepo.findByRecipientId(recipientId);
        return notifications.stream().map(NotificationMapper.INSTANCE::toNotificationDTO).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDTO> getNotificationsBySenderId(Long senderId) {
        userRepo.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + senderId));

        List<Notification> notifications = notificationRepo.findBySenderId(senderId);
        return notifications.stream().map(NotificationMapper.INSTANCE::toNotificationDTO).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO updateNotification(Long notificationId, NotificationDTO updatedNotification){
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));

        User sender = userRepo.findById(updatedNotification.getSenderId())
                .orElseThrow(() -> new ResourceNotFoundException("Sender not found with id: " + updatedNotification.getSenderId()));

        User recipient = userRepo.findById(updatedNotification.getRecipientId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found with id: " + updatedNotification.getRecipientId()));

        notification.setSender(sender); // Update the sender
        notification.setRecipient(recipient); // Update the recipient
        notification.setIsRead(updatedNotification.getIsRead());
        notification.setMessage(updatedNotification.getMessage());
//        notification.setCreatedAt(updatedNotification.getCreatedAt());

        Notification savedNotification = notificationRepo.save(notification);
        return NotificationMapper.INSTANCE.toNotificationDTO(savedNotification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + notificationId));
        notificationRepo.delete(notification);
    }
}
