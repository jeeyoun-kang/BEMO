package hello.hellospring.repository;

import hello.hellospring.entity.User;

import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findUserByUsername(String name);
}
