//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationshipTypeEnumDto {
  VISIBLE("visible"),
  CUSTOMER("customer");

  private String value;

  private RelationshipTypeEnumDto(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return this.value;
  }

  public String toString() {
    return String.valueOf(this.value);
  }

  @JsonCreator
  public static RelationshipTypeEnumDto fromValue(String value) {
    RelationshipTypeEnumDto[] var1 = values();
    int var2 = var1.length;

    for(int var3 = 0; var3 < var2; ++var3) {
      RelationshipTypeEnumDto b = var1[var3];
      if (b.value.equals(value)) {
        return b;
      }
    }

    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
