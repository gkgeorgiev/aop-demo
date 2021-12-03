package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class OrganisationController {


  @Autowired
  private BusinessRelationshipService businessRelationshipService;


  @Transactional
  @PutMapping(
      value = {"/businessRelationships"},
      produces = {"application/json", "application/xml"},
      consumes = {"application/json", "application/xml"}
  )
  public ResponseEntity<Void> putBusinessRelationships(@RequestBody final List<BusinessRelationshipDto> businessRelationshipDtos) {
    final List<BusinessRelationshipEntity> currentBusinessRelationships =
        businessRelationshipService.findAll();
    businessRelationshipService.create(businessRelationshipDtos);
    businessRelationshipService.update(currentBusinessRelationships, businessRelationshipDtos);
    businessRelationshipService.delete(currentBusinessRelationships, businessRelationshipDtos);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }


  @GetMapping(
      value = {"/businessRelationships"},
      produces = {"application/json", "application/xml"}
  )
  public ResponseEntity<List<BusinessRelationshipDto>> getBusinessRelationships() {
    return ResponseEntity.ok(businessRelationshipService.findAll().stream().map(BusinessRelationshipMapper::createDto).collect(
        Collectors.toList()));
  }
}
