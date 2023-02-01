package bemo.bemo.repository;

import bemo.bemo.entity.User;
import bemo.bemo.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository extends JpaRepository<Password, Integer> {
    Password findPasswordByUser(User user);
}
