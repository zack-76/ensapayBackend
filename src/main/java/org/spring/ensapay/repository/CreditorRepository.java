package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor,String> {

    @Query("select c.nameCreditor from Creditor c where c.codeCreditor=:codeCreditor")
    String findCreditorNameByCodeCreditor(@Param("codeCreditor") String codeCreditor);
}
