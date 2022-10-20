package hello.hellospring.repository;

import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String name);
}