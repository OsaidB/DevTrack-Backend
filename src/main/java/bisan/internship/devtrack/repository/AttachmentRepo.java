package bisan.internship.devtrack.repository;

import bisan.internship.devtrack.model.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachment, Long> {
}
