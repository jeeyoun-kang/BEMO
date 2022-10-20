package hello.hellospring.repository;

import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
}