package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Component
public class BusinessRelationshipMapper {

  private static final String SOURCE_AND_TARGET_ORGANISATIONS_CANNOT_BE_THE_SAME_UUID
          = "source and target organisations cannot be the same. UUID: ";
  private static final String INVALID_RELATIONSHIP = "Invalid business relationship: ";
  private static final String INVERSE_PREFIX = "inverse_";


  public static BusinessRelationshipDto createDto(final BusinessRelationshipEntity businessRelationship) {
    final BusinessRelationshipDto dto = new BusinessRelationshipDto();
    dto.setUuid(businessRelationship.getUuid());
    dto.setType( businessRelationship.getRelation());
    return dto;
  }

  public BusinessRelationshipEntity createEntity(final BusinessRelationshipDto dto) {
    final BusinessRelationshipEntity entityToCreate = new BusinessRelationshipEntity();
    // For non-existing objects the UUID will be generated automatically
    entityToCreate.setRelation(dto.getType());
    return entityToCreate;
  }


  public BusinessRelationshipEntity updateEntity(final BusinessRelationshipDto dto,
                                                 final BusinessRelationshipEntity existing) {
    if (dto.getUuid() != null) {
      existing.setUuid(dto.getUuid());
      existing.setRelation(invert(dto.getType()));
    } else {
      throw new UnsupportedOperationException("Uuid was not provided");
    }
    return existing;
  }

  public List<BusinessRelationshipDto> getBusinessRelationshipDtos(final String uuid,
      final List<BusinessRelationshipEntity> businessRelationships) {
    final List<BusinessRelationshipDto> businessRelationshipDtos = new ArrayList<>();
    businessRelationships.forEach((BusinessRelationshipEntity relationship) -> {
      businessRelationshipDtos.add(
              createDto(relationship));
    });
    return businessRelationshipDtos;
  }

  public List<BusinessRelationshipEntity> getEntityList(final List<BusinessRelationshipDto> dtoList) {
    final List<BusinessRelationshipEntity> entityList = new ArrayList<>();
    if (dtoList != null) {
      entityList.addAll(dtoList.stream()
          .map(BusinessRelationshipDto -> createEntity(BusinessRelationshipDto))
          .collect(Collectors.toList()));
    }
    return entityList;
  }


  private RelationshipTypeEnumDto invert(final RelationshipTypeEnumDto type) {
    final String newValue;
    if (isInverse(type)) {
      newValue = type.getValue().substring(INVERSE_PREFIX.length());
    } else {
      newValue = INVERSE_PREFIX + type.getValue();
    }
    return RelationshipTypeEnumDto.fromValue(newValue);
  }

  private boolean isInverse(final RelationshipTypeEnumDto type) {
    return type.getValue().startsWith(INVERSE_PREFIX);
  }
}
