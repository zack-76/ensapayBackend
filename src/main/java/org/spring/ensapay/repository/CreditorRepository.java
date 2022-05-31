package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor,String> {
}
