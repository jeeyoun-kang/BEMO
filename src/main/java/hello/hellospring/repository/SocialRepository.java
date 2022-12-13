package hello.hellospring.repository;

import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<SocialLogin, Integer> {
    SocialLogin findSocialLoginByUser(User user);
}
