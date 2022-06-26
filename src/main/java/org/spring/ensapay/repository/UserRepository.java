package org.spring.ensapay.repository;

import org.spring.ensapay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Modifying
    @Query("update User u set u.userPassword =:userPassword where u.username=:username")
    void updateUserPasswordByUserName(@Param("userPassword") String userPassword,@Param("username") String username);

    Optional<User> findById(String username);
    User findUserByUsername(String username);



}

