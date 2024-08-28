package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

//    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findUsersByFuncRoleFuncRoleId(Long roleId);
    @Query("SELECT u FROM User u JOIN ProjectMember pm ON u.id = pm.user.id WHERE pm.project.projectId = :projectId")
    List<User> findByProjectId(Long projectId);
}
