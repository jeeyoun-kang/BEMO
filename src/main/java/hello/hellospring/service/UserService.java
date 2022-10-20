package hello.hellospring.service;

import hello.hellospring.dto.MemberDto;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired(required = false)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException((name)));
    }
    public Long save(MemberDto memberDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));

        return userRepository.save(User.builder()
                .user_name(memberDto.getUser_name())
                .password(memberDto.getPassword())
                .build()).getUserno();
    }
}
