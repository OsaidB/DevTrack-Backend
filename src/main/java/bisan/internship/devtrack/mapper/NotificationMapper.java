package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.BoardDTO;
import bisan.internship.devtrack.dto.NotificationDTO;
import bisan.internship.devtrack.model.entity.Board;
import bisan.internship.devtrack.model.entity.Notification;
import bisan.internship.devtrack.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mappings({
            @Mapping(source = "notificationId", target = "notificationId"),
            @Mapping(source = "message", target = "message"),
            @Mapping(source = "isRead", target = "isRead"),
            @Mapping(source = "createdAt", target = "createdAt")
    })
    NotificationDTO toNotificationDTO(Notification notification);

    @Mappings({
            @Mapping(source = "notificationId", target = "notificationId"),
            @Mapping(source = "message", target = "message"),
            @Mapping(source = "isRead", target = "isRead"),
            @Mapping(source = "createdAt", target = "createdAt")
    })
    Notification toNotificationEntity(NotificationDTO notificationDTO);

    default Long map(User user) {
        return user == null ? null : user.getUserId();
    }

    default User map(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userId);
        return user;
    }
}
