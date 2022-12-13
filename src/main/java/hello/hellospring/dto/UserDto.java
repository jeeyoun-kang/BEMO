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
    private String username;
    private int loginType;

    @Builder
    public UserDto(String username, int loginType, Authentication auth, Password password) {
        this.username = username;
        this.loginType = loginType;
        this.auth = auth;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .pass(password)
                .auth(auth)
                .username(username)
                .loginType(loginType)
                .build();
    }
}
