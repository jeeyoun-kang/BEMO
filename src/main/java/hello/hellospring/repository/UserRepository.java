package hello.hellospring.repository;

import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    boolean existsUserByUsername(String name);
}