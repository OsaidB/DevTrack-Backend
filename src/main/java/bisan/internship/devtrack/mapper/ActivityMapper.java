package bisan.internship.devtrack.mapper;

import bisan.internship.devtrack.dto.ActivityDTO;
import bisan.internship.devtrack.model.entity.Activity;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;

public class ActivityMapper {
    public static ActivityDTO mapToActivityDTO(Activity activity) {
        return new ActivityDTO(
                activity.getActivityId(),
                activity.getUserId().getUserId(),
                activity.getProjectId().getProjectId(),
                activity.getTaskId().getTaskId(),
                activity.getAction(),
                activity.getCreatedAt()
        );
    }
    public static Activity mapToActivityEntity(ActivityDTO activityDTO, User user, Project project,Task task) {
        return new Activity(
                activityDTO.getActivityId(),
                user,
                project,
                task,
                activityDTO.getAction(),
                activityDTO.getCreatedAt()
        );
    }
}
