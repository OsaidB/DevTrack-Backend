package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.dto.ActivityDTO;
import bisan.internship.devtrack.exception.ResourceNotFoundException;
import bisan.internship.devtrack.mapper.ActivityMapper;
import bisan.internship.devtrack.model.entity.Activity;
import bisan.internship.devtrack.model.entity.Project;
import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.ActivityRepo;
import bisan.internship.devtrack.repository.ProjectRepo;
import bisan.internship.devtrack.repository.TaskRepo;
import bisan.internship.devtrack.repository.UserRepo;
import bisan.internship.devtrack.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepo activityRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        User user = userRepo.findById(activityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + activityDTO.getUserId()));
        Task task = taskRepo.findById(activityDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + activityDTO.getTaskId()));
        Project project = projectRepo.findById(activityDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project Not Found" + activityDTO.getProjectId()));

        Activity activity = ActivityMapper.mapToActivityEntity(activityDTO, user, project, task);
        Activity savedActivity = activityRepo.save(activity);
        return ActivityMapper.mapToActivityDTO(savedActivity);
    }

    @Override
    public ActivityDTO getActivityById(Long activityId){
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));
        return ActivityMapper.mapToActivityDTO(activity);
    }

//    @Override
//    public List<ActivityDTO> getActivitiesByTaskId(Long taskId){
//        taskRepo.findById(taskId)
//                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + taskId));
//        List<Activity> activities = activityRepo.getActivitiesByTaskId(taskId);
//        return activities.stream().map(ActivityMapper::mapToActivityDTO).collect(Collectors.toList());
//    }

    @Override
    public List<ActivityDTO> getAllActivities(){
        List<Activity> activities = activityRepo.findAll();
        return activities.stream().map(ActivityMapper::mapToActivityDTO).collect(Collectors.toList());
    }

    @Override
    public ActivityDTO updateActivity(Long activityId, ActivityDTO updateActivityDTO) {
        User user = userRepo.findById(updateActivityDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found" + updateActivityDTO.getUserId()));
        Task task = taskRepo.findById(updateActivityDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task Not Found" + updateActivityDTO.getTaskId()));
        Project project = projectRepo.findById(updateActivityDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project Not Found" + updateActivityDTO.getProjectId()));
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));

        activity.setAction(updateActivityDTO.getAction());
        activity.setUser(user);
        activity.setTask(task);
        activity.setProject(project);
//        activity.setCreatedAt(updateActivityDTO.getCreatedAt());

        Activity savedActivity = activityRepo.save(activity);
        return ActivityMapper.mapToActivityDTO(savedActivity);
    }

    @Override
    public void deleteActivity(Long activityId){
        Activity activity = activityRepo.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("Activity Not Found" + activityId));
        activityRepo.delete(activity);
    }
}
