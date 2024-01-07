package com.cap.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cap.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

}
