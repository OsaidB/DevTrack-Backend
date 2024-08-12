package bisan.internship.devtrack.service.impl;

import bisan.internship.devtrack.model.entity.Task;
import bisan.internship.devtrack.model.entity.User;
import bisan.internship.devtrack.repository.TaskRepo;
import bisan.internship.devtrack.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignmentService {

    @Autowired
    private TaskRepo taskRepository;

    @Autowired
    private UserRepo userRepository;

    public void assignTaskToUser(Task task, User assignedUser, User teamLeader) {
        // Check if the teamLeader is indeed a team leader for the role
        if (!teamLeader.getIsTeamLeader()) {
//            throw new UnauthorizedException("Only a team leader can assign tasks.");
        }

        // Check if the assignedUser is in the same role as the teamLeader
        if (!teamLeader.getRole().equals(assignedUser.getRole())) {
//            throw new UnauthorizedException("Team leader can only assign tasks to members of their team.");
        }

        // Assign the task to the user
        task.setAssignedTo(assignedUser);
        task.setStatus("Assigned");

        // Save the task
        taskRepository.save(task);

        // Log the assignment (optional)
        logTaskAssignment(task, assignedUser, teamLeader);
    }

    private void logTaskAssignment(Task task, User assignedUser, User teamLeader) {
        // Logic for logging task assignments
        System.out.println("Task " + task.getTaskName() + " assigned to " + assignedUser.getUsername() + " by " + teamLeader.getUsername());
    }
}
