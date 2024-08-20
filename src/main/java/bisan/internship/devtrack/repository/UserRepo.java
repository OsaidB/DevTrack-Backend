package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);
    List<User> findUsersByRoleRoleId(Long roleId);
    @Query("SELECT u FROM User u JOIN ProjectMember pm ON u.id = pm.user.userId WHERE pm.project.projectId = :projectId")
    List<User> findByProjectId(Long projectId);
}
