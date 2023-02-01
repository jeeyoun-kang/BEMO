package hello.hellospring.repository;

import hello.hellospring.entity.Password;
import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository extends JpaRepository<Password, Integer> {
    Password findPasswordByUser(User user);
}
