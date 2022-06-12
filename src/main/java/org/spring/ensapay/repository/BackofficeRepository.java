package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BackofficeRepository extends JpaRepository<Backoffice,Long> {

    //Backoffice findByBackofficeUser(String usename);
    @Query(value="SELECT b FROM Backoffice b  WHERE b.backofficeUser.username=:username")
    Backoffice findAgentByIdentifiant (@Param(value = "username") String username);
}
