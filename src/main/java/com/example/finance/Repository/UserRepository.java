package com.example.finance.Repository;

import com.example.finance.entites.Alert;
import com.example.finance.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);

    List<User> findAll();

    User findByCin(Long cin);

}
