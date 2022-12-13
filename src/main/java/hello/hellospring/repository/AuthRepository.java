package hello.hellospring.repository;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authentication, Integer> {
    Authentication findAuthenticationByUser(User user);
}
