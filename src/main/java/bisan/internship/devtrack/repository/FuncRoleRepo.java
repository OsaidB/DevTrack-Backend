package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.FunctionalRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncRoleRepo extends JpaRepository<FunctionalRole, Long> {
    FunctionalRole findByRoleName(String roleName);
}
