package org.spring.ensapay.repository;

import org.spring.ensapay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Modifying
    @Query("update User u set u.userPassword =:userPassword where u.userName=:userName")
    void updateUserPasswordByUserName(@Param("userPassword") String userPassword,@Param("userName") String userName);
}

