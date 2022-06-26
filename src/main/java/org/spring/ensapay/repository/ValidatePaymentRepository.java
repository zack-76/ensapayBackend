package org.spring.ensapay.repository;

import org.spring.ensapay.entity.ValidatePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidatePaymentRepository extends JpaRepository<ValidatePayment,String> {

    ValidatePayment findByUsername(String username);
}
