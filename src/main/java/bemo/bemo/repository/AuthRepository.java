package bemo.bemo.repository;

import bemo.bemo.entity.User;
import bemo.bemo.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authentication, Integer> {
    Authentication findAuthenticationByUser(User user);
}
