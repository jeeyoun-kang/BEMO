package hello.hellospring.dto;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserDto {
    private Password password;
    private Authentication auth;
    private SocialLogin socialLogin;
    private String username;
    private int loginType;

    @Builder
    public UserDto(String username, int loginType, Authentication auth, Password password, SocialLogin socialLogin) {
        this.username = username;
        this.loginType = loginType;
        this.auth = auth;
        this.password = password;
        this.socialLogin = socialLogin;
    }

    public void setPassword(String password) {
        this.password.updatePassword(password);
    }

    public User toEntity() {
        return User.builder()
                .pass(password)
                .auth(auth)
                .social(socialLogin)
                .username(username)
                .loginType(loginType)
                .build();
    }
}
