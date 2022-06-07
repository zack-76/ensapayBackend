package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Long> {


    @Query("select c.clientSolde from Client c where c.clientId=:clientId")
    Integer findClientSoldeByClientId(@Param("clientId") Long clientId);


    @Query("select c.clientEmail from Client c where c.clientId=:clientId")
    String findClientEmailByClientId(@Param("clientId") Long clientId);


    @Query("select c.clientFirstName from Client c where c.clientId=:clientId")
    String findClientFirstNameByClientId(@Param("clientId") Long clientId);

    @Query("select c.clientLastName from Client c where c.clientId=:clientId")
    String findClientLastNameByClientId(@Param("clientId") Long clientId);


}
