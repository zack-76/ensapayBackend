package org.spring.ensapay.repository;

import org.spring.ensapay.entity.Backoffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackofficeRepository extends JpaRepository<Backoffice,Long> {

    //Backoffice findByBackofficeUser(String usename);
}
