package hello.hellospring.repository;

import hello.hellospring.entity.User;

import java.util.Optional;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Override
    public Optional<User> findUserByUsername(String name) {
        return Optional.empty();
    }
}