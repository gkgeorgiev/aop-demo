package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessRelationshipService {

  @Autowired
  private BusinessRelationshipRepository businessRelationshipRepository;

  @Autowired
  private BusinessRelationshipMapper businessRelationshipMapper;
  /* Creates business relationships in the input organisation. */
  public void create(final List<BusinessRelationshipDto> businessRelationshipDtos) {
    final List<BusinessRelationshipEntity> businessRelationshipsToCreate = businessRelationshipDtos
            .stream()
            .filter(dto -> dto.getUuid() == null) // non null case is considered in update/delete
            .map(dto -> businessRelationshipMapper.createEntity(dto))
            .collect(Collectors.toList());
    businessRelationshipRepository.saveAll(businessRelationshipsToCreate);
  }

  /* Updates business relationships, based on the input businessRelationshipDtos,
   * which were existing in the given list of existingBusinessRelationships
   * in the input organisation. */
  public void update(final List<BusinessRelationshipEntity> existingBusinessRelationships,
                     final List<BusinessRelationshipDto> businessRelationshipDtos) {
    final List<BusinessRelationshipEntity> businessRelationshipsToUpdate = new ArrayList<>();
    for (final BusinessRelationshipDto dto: businessRelationshipDtos) {
      if (dto.getUuid() != null) {
        final Optional<BusinessRelationshipEntity> found = existingBusinessRelationships.stream()
                .filter(bu -> dto.getUuid().equals(bu.getUuid())).findFirst();
        final BusinessRelationshipEntity existing = found.orElseThrow(() ->
                new RuntimeException(dto.getUuid()));
        existing.setRelation(dto.getType());
        businessRelationshipsToUpdate.add(existing);
      }
    }
    businessRelationshipRepository.saveAll(businessRelationshipsToUpdate);
  }

  /* Deletes business relationships, based on the input businessRelationshipDtos,
   * which were existing in the given list of existingBusinessRelationships. */
  public void delete(final List<BusinessRelationshipEntity> existingBusinessRelationships,
                     final List<BusinessRelationshipDto> businessRelationshipDtos) {
    // Copy of the existing elements,
    // all are candidates to be deleted until they are found in the input.
    final List<BusinessRelationshipEntity> businessRelationshipsToDelete =
            new ArrayList<>(existingBusinessRelationships);
    for (final BusinessRelationshipDto dto: businessRelationshipDtos) {
      if (dto.getUuid() != null) {
        final Optional<BusinessRelationshipEntity> found = existingBusinessRelationships.stream()
                .filter(bu -> dto.getUuid().equals(bu.getUuid())).findFirst();
        if (found.isPresent()) {
          businessRelationshipsToDelete.remove(found.get());
        }
      }
    }
    businessRelationshipRepository.deleteAll(businessRelationshipsToDelete);
  }

  public List<BusinessRelationshipEntity> findAll() {
    return this.businessRelationshipRepository.findAll();
  }
}