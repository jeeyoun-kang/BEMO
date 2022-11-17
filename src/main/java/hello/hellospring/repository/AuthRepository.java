package hello.hellospring.repository;

import hello.hellospring.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authentication, Integer> {
}
