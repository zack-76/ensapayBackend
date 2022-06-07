package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Integer> {
}
