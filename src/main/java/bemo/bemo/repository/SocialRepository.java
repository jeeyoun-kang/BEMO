package bemo.bemo.repository;

import bemo.bemo.entity.SocialLogin;
import bemo.bemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<SocialLogin, Integer> {
    SocialLogin findSocialLoginByUser(User user);
}
