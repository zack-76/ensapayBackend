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
    @Query("update User u set u.userPassword =:userPassword where u.userPhone=:userPhone")
    void updateUserPasswordByUserName(@Param("userPassword") String userPassword,@Param("userPhone") String userPhone);

    @Query("select u.clientSolde from User u where u.userPhone=:userPhone")
    Integer findClientSoldeByUserName(String userPhone);
}

