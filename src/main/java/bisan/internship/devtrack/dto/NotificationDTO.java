package bisan.internship.devtrack.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDTO {

    private Long notificationId;
    private Long userId;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;

}
