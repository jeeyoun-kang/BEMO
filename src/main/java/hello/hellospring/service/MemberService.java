package hello.hellospring.service;

import hello.hellospring.dto.MemberDto;
import hello.hellospring.entity.User;
import hello.hellospring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        return memberRepository.findUserByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException((name)));
    }
    public Long save(MemberDto memberDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));

        return memberRepository.save(User.builder()
                .user_name(memberDto.getUser_name())
                .password(memberDto.getPassword())
                .build()).getUserno();
    }
}
