package com.intro.IntroductionREST.repository;

import com.intro.IntroductionREST.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
    Document save(Document document);
}