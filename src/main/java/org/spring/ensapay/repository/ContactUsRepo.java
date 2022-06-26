package org.spring.ensapay.repository;

import org.spring.ensapay.entity.ConatctUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactUsRepo extends JpaRepository<ConatctUs,Long> {
    List <ConatctUs> findByIdAgent(Long id);
    @Query(
            value = "SELECT * FROM Conatct_us WHERE id_agent=?1 AND phone LIKE ?2%",nativeQuery = true
    )
    List<ConatctUs> findByIdAgentAndPhoneeee(Long id, String phone);
}
