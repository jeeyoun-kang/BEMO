package bemo.bemo.repository;

import bemo.bemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    boolean existsUserByUsername(String name);
}