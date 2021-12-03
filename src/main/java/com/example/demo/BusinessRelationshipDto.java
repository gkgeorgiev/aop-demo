//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class BusinessRelationshipDto {
  @JsonProperty("uuid")
  private String uuid;
  private RelationshipTypeEnumDto type;

  public BusinessRelationshipDto() {
  }

  public BusinessRelationshipDto uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  public String getUuid() {
    return this.uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  @NotNull
  public RelationshipTypeEnumDto getType() {
    return this.type;
  }

  public void setType(RelationshipTypeEnumDto type) {
    this.type = type;
  }



}
