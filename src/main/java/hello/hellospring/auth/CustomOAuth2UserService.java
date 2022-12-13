package hello.hellospring.auth;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomOAuth2UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();
        if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;  			// 사용자가 입력한 적은 없지만 만들어준다

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);  // 사용자가 입력한 적은 없지만 만들어준다

        String birthday = oAuth2User.getAttribute("birthday");
        String cellphone = oAuth2User.getAttribute("mobile");
        Authentication.Role role = Authentication.Role.ROLE_USER;

        
        // 현재 정보 가져오기
        User byUsername = userRepository.findByUsername(username);
        Password pass = null;
        Authentication auth = null;
        SocialLogin social = null;
        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            LocalDateTime now =  LocalDateTime.now();
            pass = Password.builder().password(password).update_date(now).build();
            auth = Authentication.builder().role(role).birthday(birthday).cell_phone(cellphone).auth_date(now).build();
            social = SocialLogin.builder().socialCode(0).externalId(providerId).accessToken("www").build(); // 이거만 하면 될듯
            byUsername = User.builder().username(username).loginType(1).pass(pass).auth(auth).social(social)
                    .build();
            userRepository.save(byUsername);
        }

        return new PrincipalDetails( byUsername, auth, pass, social, oAuth2User.getAttributes());
    }
}