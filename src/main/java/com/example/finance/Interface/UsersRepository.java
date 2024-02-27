package com.example.finance.Interface;


import com.example.finance.entites.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.passwordToken = ?1")
    public User findByPasswordToken(String token);

    @Query("SELECT u FROM User u WHERE u.cin = ?1")
    public User findByCin(Long cin);

}