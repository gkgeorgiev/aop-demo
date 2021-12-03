package com.example.demo;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Log
@Component
class BusinessRelationshipAspect {

  private final BusinessRelationshipRepository relationshipRepository;
  @PersistenceContext
  private EntityManager entityManager;



  @Autowired
  public BusinessRelationshipAspect(
      final BusinessRelationshipRepository relationshipRepository) {
    this.relationshipRepository = relationshipRepository;
  }

  /**
   * The advice pointcut definition is based on the BusinessRelationshipRepository.
   */
  @Around(
      "execution(* com.example.demo.BusinessRelationshipRepository"
          + ".save*(..))")
  public Object onBusinessRelationshipSaveAll(final ProceedingJoinPoint joinPoint)
      throws Throwable {
    if (!(joinPoint.getArgs()[0] instanceof Collection)) {
      throw new RuntimeException("Supporting JMS events messaging for business relationships only "
          + "for Collections ."); //NOSONAR
    }


    final Collection<BusinessRelationshipEntity> relationshipsAfter =
        (Collection<BusinessRelationshipEntity>) joinPoint.proceed();

    for (final BusinessRelationshipEntity newRel : relationshipsAfter) {
      log.info("Save " + newRel.getUuid());
    }
    return relationshipsAfter;
  }

  /**
   * The advice pointcut definition is based on the BusinessRelationshipRepository.
   */
  @Around(
      "execution(* com.example.demo.BusinessRelationshipRepository"
          + ".deleteAll(..))")
  public Object onBusinessRelationshipDeleteAll(final ProceedingJoinPoint joinPoint)
      throws Throwable {
    if (!(joinPoint.getArgs()[0] instanceof Collection)) {
      throw new RuntimeException("JMS event messaging for deleting business relationship supported"
          + "only for Collections ."); //NOSONAR
    }

    final Collection<BusinessRelationshipEntity> relationships =
        (Collection<BusinessRelationshipEntity>) joinPoint.proceed();

    for (final BusinessRelationshipEntity rel : relationships) {
      log.info("Delete " + rel.getUuid());
    }
    return relationships;
  }


}