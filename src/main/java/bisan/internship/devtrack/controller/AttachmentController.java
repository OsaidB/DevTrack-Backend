package bisan.internship.devtrack.controller;

import bisan.internship.devtrack.dto.AttachmentDTO;
import bisan.internship.devtrack.service.AttachmentService;
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
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private final AttachmentService attachmentService;

    @PostMapping
    public ResponseEntity<AttachmentDTO> createAttachment(@Valid  @RequestBody AttachmentDTO attachmentDTO) {
        AttachmentDTO createAttachment = attachmentService.createAttachment(attachmentDTO);
        return new ResponseEntity<>(createAttachment, HttpStatus.CREATED);
    }

    @GetMapping("{attachmentId}")
    public ResponseEntity<AttachmentDTO> getAttachment(@PathVariable("attachmentId") long attachmentId) {
        AttachmentDTO getAttachment = attachmentService.getAttachmentById(attachmentId);
        return ResponseEntity.ok(getAttachment);
    }

    @GetMapping
    public ResponseEntity<List<AttachmentDTO>> getAllAttachments() {
        List<AttachmentDTO> getAllAttachment = attachmentService.getAllAttachments();
        return ResponseEntity.ok(getAllAttachment);
    }

    @PutMapping("{attachmentId}")
    public ResponseEntity<AttachmentDTO> updateAttachment(@PathVariable("attachmentId") long attachmentId
                                                            ,@Valid @RequestBody AttachmentDTO updateAttachmentDTO) {
        AttachmentDTO attachmentDTO = attachmentService.updateAttachment(attachmentId, updateAttachmentDTO);
        return ResponseEntity.ok(attachmentDTO);
    }

    @DeleteMapping("{attachmentId}")
    public ResponseEntity<String> deleteAttachment(@PathVariable("attachmentId") long attachmentId) {
        attachmentService.deleteAttachment(attachmentId);
        return ResponseEntity.ok("Attachment deleted successfully");
    }
}
