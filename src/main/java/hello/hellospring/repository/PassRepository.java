package hello.hellospring.repository;

import hello.hellospring.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository extends JpaRepository<Password, Integer> {
}
