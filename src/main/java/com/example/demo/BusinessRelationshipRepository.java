package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;


public interface BusinessRelationshipRepository
    extends JpaRepository<BusinessRelationshipEntity, String> {

}