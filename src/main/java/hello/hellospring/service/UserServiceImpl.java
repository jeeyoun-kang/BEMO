package hello.hellospring.service;

import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByusername(name)
                .orElseThrow(() -> {return new UsernameNotFoundException(name+" 유저가 존재하지 않습니다.");});
        return new User(user);
    }
}