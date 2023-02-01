package bemo.bemo.service;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.entity.Authentication;
import bemo.bemo.entity.Password;
import bemo.bemo.entity.SocialLogin;
import bemo.bemo.entity.User;
import bemo.bemo.repository.AuthRepository;
import bemo.bemo.repository.PassRepository;
import bemo.bemo.repository.SocialRepository;
import bemo.bemo.repository.UserRepository;
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
