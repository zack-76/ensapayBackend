package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BackofficeRepository extends JpaRepository<Backoffice,Long> {
    @Query(value="SELECT backoffice FROM Backoffice backoffice WHERE backoffice.backofficeUser.username=:username")
    Backoffice findBackOfficeByIdentifiant (@Param(value = "username") String username);

    //Backoffice findByBackofficeUser(String usename);
}
