package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.ProjectMemberDTO;
import bisan.internship.devtrack.service.ProjectMemberService;
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
    public ResponseEntity<ProjectMemberDTO> createProjectMember(@RequestBody ProjectMemberDTO projectMemberDTO) {
        ProjectMemberDTO createdProjectMember = projectMemberService.createProjectMember(projectMemberDTO);
        return new ResponseEntity<>(createdProjectMember, HttpStatus.CREATED);
    }

    @GetMapping("{projectMemberId}")
    public ResponseEntity<ProjectMemberDTO> getProjectMemberById(@PathVariable("projectMemberId") long projectMemberId) {
        ProjectMemberDTO projectMemberDTO = projectMemberService.getProjectMemberById(projectMemberId);
        return ResponseEntity.ok(projectMemberDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectMemberDTO>> getAllProjectMembers() {
        List<ProjectMemberDTO> projectMembers = projectMemberService.getAllProjectMembers();
        return ResponseEntity.ok(projectMembers);
    }

    @PutMapping("{projectMemberId}")
    public ResponseEntity<ProjectMemberDTO> updateProjectMember(@PathVariable("projectMemberId") long projectMemberId,
                                                                @RequestBody ProjectMemberDTO updatedProjectMember) {
        ProjectMemberDTO projectMemberDTO = projectMemberService.updateProjectMember(projectMemberId, updatedProjectMember);
        return ResponseEntity.ok(projectMemberDTO);
    }

    @DeleteMapping("{projectMemberId}")
    public ResponseEntity<String> deleteProjectMember(@PathVariable("projectMemberId") long projectMemberId) {
        projectMemberService.deleteProjectMember(projectMemberId);
        return ResponseEntity.ok("Project member deleted successfully");
    }
}
