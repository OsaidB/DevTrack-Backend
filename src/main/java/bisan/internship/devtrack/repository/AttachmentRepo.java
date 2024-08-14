package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
    List<Attachment> findAttachmentByTaskTaskId(Long taskId);
}
