package hello.hellospring.auth;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class PrincipalDetails implements UserDetails, OAuth2User{
    private User user;
    private Authentication auth;
    private Password pass;
    private SocialLogin socialLogin;
    private OAuth2UserInfo oAuth2UserInfo;

    //UserDetails : Form 로그인 시 사용
    public PrincipalDetails(User user, Authentication auth, Password pass, SocialLogin social) {
        this.user = user;
        this.auth = auth;
        this.pass=  pass;
        this.socialLogin = social;
    }

    //OAuth2User : OAuth2 로그인 시 사용
    public PrincipalDetails(User user, Authentication auth, Password pass, SocialLogin social, OAuth2UserInfo oAuth2UserInfo) {
        //PrincipalOauth2UserService 참고
        this.user = user;
        this.auth = auth;
        this.pass=  pass;
        this.socialLogin = social;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2UserInfo.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> auth.getRole().toString());
        return collect;
    }
    @Override
    public String getPassword() {
        return pass.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return oAuth2UserInfo.getProviderId();
    }
}
