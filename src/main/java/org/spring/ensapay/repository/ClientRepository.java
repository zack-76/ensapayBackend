package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {


   @Query("select c.clientSolde from Client c where c.clientUser.username=:username")
    Integer findClientSoldeByClientId(@Param("username") String username);

    @Query("select c.clientEmail from Client c where c.clientUser.username=:username")
    String findClientByClientUserUsername(@Param("username") String username);


    @Query("select c.clientEmail from Client c where c.clientId=:clientId")
    String findClientEmailByClientId(@Param("clientId") Long clientId);

 @Query("select c.clientFullName from Client c where c.clientUser.username=:username")
 String findClientFullNameByClientUserUsername(@Param("username") String username);



     @Query(value="SELECT client FROM Client client  WHERE client.clientUser.username=:username")
     Client findClientByIdentifiant (@Param(value = "username") String username);

    @Modifying
    @Query("update Client c set c.clientSolde =:clientSolde where c.clientPhone=:phone")
    void updateClientSoldeByClientId(@Param("clientSolde") Integer clientSolde,@Param("phone") String phone);
    @Modifying
    @Query(value = "UPDATE Client client SET client.firstConnection=false WHERE client.clientUser.username=:username")
    int UpdateFirstConnectionAgentByid(@Param(value = "username") String username);

    List<Client> findByidAgent(Long id);
    @Query(
            value = "SELECT * FROM Client WHERE id_agent=?1 AND client_full_name LIKE ?2%",nativeQuery = true
    )
    List<Client> findByidAgentAndName(Long id, String name);
}
