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
    private String userName;
    private int loginType;

    @Builder
    public UserDto(String userName, int loginType, Authentication auth, Password password, SocialLogin socialLogin) {
        this.userName = userName;
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
                .password(password)
                .auth(auth)
                .social(socialLogin)
                .userName(userName)
                .loginType(loginType)
                .build();
    }
}
