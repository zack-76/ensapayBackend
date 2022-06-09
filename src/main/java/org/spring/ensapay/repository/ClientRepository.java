package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


   // @Query("select c.clientSolde from Client c where c.clientId=:clientId")
    Integer findClientSoldeByClientId(@Param("clientId") Long clientId);

    @Query("select c.clientEmail from Client c where c.clientUser.username=:username")
    String findClientByClientUserUsername(@Param("username") String username);
}
