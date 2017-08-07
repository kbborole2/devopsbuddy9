package com.devopsbuddy9.backend.persistence.repositories;

import com.devopsbuddy9.backend.persistence.domain.backend.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by kb on 7/19/2017.
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);

   User findByEmail(String email);

    @Modifying
    @Query("update User u set  u.password = :password where u.id = :userId")
    void updateUserPassword(@Param("userId") long userId, @Param("password") String password);


}
