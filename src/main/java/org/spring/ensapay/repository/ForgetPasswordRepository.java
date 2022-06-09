package org.spring.ensapay.repository;


import org.spring.ensapay.entity.ForgetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword,String> {

    Optional<ForgetPassword> findById(String username);
}
