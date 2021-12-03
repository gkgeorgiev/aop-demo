package com.example.demo;


import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.envers.Audited;


/**
 * Implementation of a {@code Business Relationship} resource entity.
 */
@Entity
@Audited
@Access(value = AccessType.FIELD)
@Table(
    name = BusinessRelationshipEntity.TABLE_NAME)
@Data
@SuppressWarnings({"nls", "restriction"})
public class BusinessRelationshipEntity {


  /**
   * The database table name (value: <code>{@value}</code>).
   */
  static final String TABLE_NAME = "BUSINESS_REL";

  /**
   * The {@code UUID} feature (primary key).
   */
  @Id
  @Column(name = "UUID", unique = true, nullable = false, length = 23)
  @GeneratedValue(generator = "uuid_generator")
  private String uuid;
  /**
   * The {@code Stamp} feature.
   */
  @Column(name = "STAMP", unique = false, nullable = false)
  private Long stamp = Long.valueOf(0);
  /**
   * The {@code Archived} feature.
   */
  @Column(
      name = "ARCHIVED",
      unique = false,
      nullable = false,
      columnDefinition = "NUMBER(1) default 0")
  private Boolean archived = Boolean.FALSE;

  /**
   * The {@code Business Relation} feature.
   */
  @Column(name = "RELATION", unique = false, nullable = false)
  @Enumerated(EnumType.STRING)
  private RelationshipTypeEnumDto relation;

  /**
   * The {@code Organisaton} feature.
   */
  /**
   * The {@code Modified By} feature.
   */
  @Column(name = "HIST_USR", unique = false, nullable = false, length = 60)
  private String modifiedBy;

  @PrePersist
  private void prePersist() {
    stamp = 1L;
  }

  @PreUpdate
  private void preUpdate() {
    stamp++;
  }
}
