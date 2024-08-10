package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/project-members")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @PostMapping
    public ResponseEntity<ProjectMemberDTO> addProjectMember(@Valid @RequestBody ProjectMemberDTO projectMemberDTO) {
        ProjectMemberDTO createdProjectMember = projectMemberService.addProjectMember(projectMemberDTO);
        return new ResponseEntity<>(createdProjectMember, HttpStatus.CREATED);
    }

    @GetMapping("{projectMemberId}")
    public ResponseEntity<ProjectMemberDTO> getProjectMemberById(@PathVariable("projectMemberId") long projectMemberId) {
        ProjectMemberDTO projectMemberDTO = projectMemberService.getProjectMemberById(projectMemberId);
        return ResponseEntity.ok(projectMemberDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectMemberDTO>> getAllProjectMembers() {
        List<ProjectMemberDTO> projectMembersDTO = projectMemberService.getAllProjectMembers();
        return ResponseEntity.ok(projectMembersDTO);
    }

    @PutMapping("{projectMemberId}")
    public ResponseEntity<ProjectMemberDTO> updateProjectMember(@PathVariable("projectMemberId") long projectMemberId,
                                                                @Valid @RequestBody ProjectMemberDTO updatedProjectMemberDTO) {
        ProjectMemberDTO projectMemberDTO = projectMemberService.updateProjectMember(projectMemberId, updatedProjectMemberDTO);
        return ResponseEntity.ok(projectMemberDTO);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteProjectMember(@PathVariable("userId") long userId) {
        projectMemberService.deleteProjectMemberFromAllProjects(userId);
        return ResponseEntity.ok("Project Member Deleted Successfully from All Projects");
    }

    @DeleteMapping("/project/{projectId}/member/{projectMemberId}")
    public ResponseEntity<String> deleteProjectMemberFromProject(
            @PathVariable("projectId") Long projectId,
            @PathVariable("projectMemberId") Long projectMemberId) {
        projectMemberService.deleteProjectMemberFromProject(projectId, projectMemberId);
        return ResponseEntity.ok("Project Member Deleted Successfully from Project");
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectMemberDTO>> getMembersByProjectId(@PathVariable("projectId") long projectId) {
        List<ProjectMemberDTO> projectMembersDTO = projectMemberService.getMembersByProjectId(projectId);
        return ResponseEntity.ok(projectMembersDTO);
    }
}
