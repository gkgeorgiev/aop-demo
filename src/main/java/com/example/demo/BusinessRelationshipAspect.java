package com.example.demo;

import java.util.Collection;
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

  @Autowired
  public BusinessRelationshipAspect(final BusinessRelationshipRepository relationshipRepository) {
    this.relationshipRepository = relationshipRepository;
  }

  @Around("execution(* com.example.demo.BusinessRelationshipRepository.save*(..))")
  public Object onBusinessRelationshipSaveAll(final ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("### " + joinPoint);
    if (!(joinPoint.getArgs()[0] instanceof Collection))
      throw new RuntimeException("Supporting JMS events messaging for business relationships only for Collections"); //NOSONAR
    final Collection<BusinessRelationshipEntity> relationshipsAfter = (Collection<BusinessRelationshipEntity>) joinPoint.proceed();
    if (relationshipsAfter != null) {
      for (final BusinessRelationshipEntity newRel : relationshipsAfter)
        log.info("Save " + newRel.getUuid());
    }
    return relationshipsAfter;
  }

  @Around("execution(* com.example.demo.BusinessRelationshipRepository.deleteAll(..))")
  public Object onBusinessRelationshipDeleteAll(final ProceedingJoinPoint joinPoint) throws Throwable {
    log.info("### " + joinPoint);
    if (!(joinPoint.getArgs()[0] instanceof Collection))
      throw new RuntimeException("JMS event messaging for deleting business relationship supported only for Collections"); //NOSONAR
    final Collection<BusinessRelationshipEntity> relationships = (Collection<BusinessRelationshipEntity>) joinPoint.proceed();
    if (relationships != null) {
      for (final BusinessRelationshipEntity rel : relationships)
        log.info("Delete " + rel.getUuid());
    }
    return relationships;
  }

}
