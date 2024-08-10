package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.NotificationDTO;
import bisan.internship.devtrack.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@Valid  @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @GetMapping("{notificationId}")
    public ResponseEntity<NotificationDTO> getNotification(@PathVariable("notificationId") long notificationId) {
        NotificationDTO notificationDTO = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notificationDTO);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("{notificationId}")
    public ResponseEntity<NotificationDTO> updateNotification(@PathVariable("notificationId") long notificationId
                                                                ,@Valid @RequestBody NotificationDTO updatedNotificationDTO) {
        NotificationDTO notificationDTO = notificationService.updateNotification(notificationId, updatedNotificationDTO);
        return ResponseEntity.ok(notificationDTO);
    }

    @DeleteMapping("{notificationId}")
    public ResponseEntity<String> deleteNotification(@PathVariable("notificationId") long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification Deleted successfully");
    }
}