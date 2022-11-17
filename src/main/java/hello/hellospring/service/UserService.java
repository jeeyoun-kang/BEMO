package hello.hellospring.service;

import hello.hellospring.dto.UserDto;
import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
                .orElseThrow(() -> new UsernameNotFoundException((name+" 유저가 존재하지 않습니다.")));
    }

    @Transactional
    public Long create(UserDto userDto) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword().getPassword()));

        return userRepository.save(userDto.toEntity()).getUserNo();
    }

    @Transactional
    public Long update(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        user.update(userDto.getUserName(), userDto.getLoginType(), userDto.getAuth(), userDto.getPassword(), userDto.getSocialLogin());
        return id;
    }
    @Transactional
    public String updatePass(String userName, String password, UserDto userDto) {
        User user = loadUserByUsername(userName);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(password));

        user.update(userDto.getUserName(), userDto.getLoginType(), userDto.getAuth(), userDto.getPassword(), userDto.getSocialLogin());
        return userName;
    }
    
    //회원가입

}
