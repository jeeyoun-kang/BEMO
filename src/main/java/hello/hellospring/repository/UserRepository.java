package hello.hellospring.repository;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

}

