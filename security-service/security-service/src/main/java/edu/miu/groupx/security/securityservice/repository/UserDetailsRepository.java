package edu.miu.groupx.security.securityservice.repository;

import edu.miu.groupx.security.securityservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String name);
}
