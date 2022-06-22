package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent,Long> {

    @Query(value="SELECT agent FROM Agent agent WHERE agent.agentUser.username=:username")
    Agent findAgentByIdentifiant (@Param(value = "username") String username);

    @Modifying
    @Query(value = "UPDATE Agent agent SET agent.firstConnection=false WHERE agent.agentUser.username=:username")
    int UpdateFirstConnectionAgentByid(@Param(value = "username") String username);



    List<Agent> findByIdbackOffice(Long id);
    @Query(
            value = "SELECT * FROM Agent WHERE idback_office=?1 AND agent_full_name LIKE ?2%",nativeQuery = true
    )
    List<Agent> findByIdbackOfficeAndname(Long id, String name);
}
