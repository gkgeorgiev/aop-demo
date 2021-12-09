package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

import static com.example.demo.RelationshipTypeEnumDto.CUSTOMER;
import static com.example.demo.RelationshipTypeEnumDto.VISIBLE;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    try (ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args)) {
      OrganisationController organisationController = context.getBean(OrganisationController.class);
      List<BusinessRelationshipDto> dtos = List.of(
        createDto(VISIBLE, "_7JkS2Nty-l2S0emLjV_kW0"),
        createDto(VISIBLE, "_-Phh-cdigrk_TICVqXRv8H"),
        createDto(CUSTOMER, "_fLdHz86UEeujNIM3TF9ZaQ"),
        createDto(VISIBLE, "_fLdH1c6UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_fLdH1M6UEeujNIM3TF9ZaQ"),
        createDto(VISIBLE, "_fLdvLs6UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_fLdHyc6UEeujNIM3TF9ZaQ"),
        createDto(VISIBLE, "_fLdHys6UEeujNIM3TF9ZaQ"),
        createDto(VISIBLE, "_fLdH0c6UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_fLdvLc6UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_rr_H_Q_2oYFNapCL9RQiDC"),
        createDto(VISIBLE, "_fLdH0M6UEeujNIM3TF9ZaQ"),
        createDto(VISIBLE, "_fLdHxs6UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_ITufRSYr0yOOnpnzJwMcdq"),
        createDto(VISIBLE, "_fLdHw86UEeujNIM3TF9ZaQ"),
        createDto(CUSTOMER, "_fLdHws6UEeujNIM3TF9ZaQ")
      );
      organisationController.putBusinessRelationships(dtos);
    }
  }

  private static BusinessRelationshipDto createDto(RelationshipTypeEnumDto type, String uuid) {
    BusinessRelationshipDto dto;
    dto = new BusinessRelationshipDto();
    dto.setType(type);
    dto.setUuid(uuid);
    return dto;
  }

}
