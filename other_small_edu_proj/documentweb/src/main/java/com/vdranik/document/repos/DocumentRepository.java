package com.vdranik.document.repos;

import com.vdranik.document.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
