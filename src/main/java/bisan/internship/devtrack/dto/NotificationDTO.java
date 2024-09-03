package bisan.internship.devtrack.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Long notificationId;

    @NotNull(message = "Sender ID cannot be null")
    private Long senderId; // New field for sender ID

    @NotNull(message = "Recipient ID cannot be null")
    private Long recipientId; // Renamed field for recipient ID

    @NotNull(message = "Message cannot be null")
    @Size(max = 255, message = "Message must be less than 255 characters")
    private String message;

    private Boolean isRead;
    private LocalDateTime createdAt;

}
