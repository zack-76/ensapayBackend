package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactureRepository extends JpaRepository<Facture,Integer> {

    List<Facture> findByClientName(String clientName);
}
