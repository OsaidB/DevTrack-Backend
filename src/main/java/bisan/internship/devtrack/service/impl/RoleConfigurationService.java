package bisan.internship.devtrack.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
public class RoleConfigurationService {

    // Use an in-memory store for demo purposes; in production, consider a persistent store
    private Long backendRoleId;
    private Long frontendRoleId;
    private Long qaRoleId;

}

