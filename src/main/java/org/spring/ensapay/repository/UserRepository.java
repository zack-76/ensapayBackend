package org.spring.ensapay.repository;

import org.spring.ensapay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
/*
    @Query("update c from user where c.email ")
    void resetPassword();
 */
}

