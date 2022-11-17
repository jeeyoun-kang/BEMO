package hello.hellospring.repository;

import hello.hellospring.entity.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<SocialLogin, Integer> {
}
