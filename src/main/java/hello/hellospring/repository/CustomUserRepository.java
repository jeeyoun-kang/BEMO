package hello.hellospring.repository;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findUserByUsername(String name);




}
