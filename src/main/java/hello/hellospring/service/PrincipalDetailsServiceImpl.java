package hello.hellospring.service;

import hello.hellospring.auth.PrincipalDetails;
import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import hello.hellospring.repository.AuthRepository;
import hello.hellospring.repository.PassRepository;
import hello.hellospring.repository.SocialRepository;
import hello.hellospring.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class PrincipalDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private AuthRepository authRepository;
    private PassRepository passRepository;
    private SocialRepository socialRepository;

    public PrincipalDetailsServiceImpl(UserRepository userRepository, AuthRepository authRepository, PassRepository passRepository, SocialRepository socialRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.passRepository = passRepository;
        this.socialRepository = socialRepository;
    }

    @Override
    public PrincipalDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);
        Authentication authByUser = authRepository.findAuthenticationByUser(byUsername);
        Password passByUser = passRepository.findPasswordByUser(byUsername);
        SocialLogin socialByUser = socialRepository.findSocialLoginByUser(byUsername);
        if(byUsername != null){
            return new PrincipalDetails(byUsername, authByUser, passByUser, socialByUser);
        }
        return null;
    }
}
