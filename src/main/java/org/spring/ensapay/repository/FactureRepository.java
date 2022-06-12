package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Integer> {

    List<Facture> findByNumeroClient(String numeroClient);

    @Query(
            value = "SELECT * FROM Facture WHERE numero_client=?1 AND creditor_name LIKE ?2%",nativeQuery = true
            )
    List<Facture> findByClientNameAndCreditorName(String numeroClients, String creditor);
}
