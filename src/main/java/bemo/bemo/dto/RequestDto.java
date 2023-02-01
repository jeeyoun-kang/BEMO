package hello.hellospring.dto;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.SocialLogin;
import hello.hellospring.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RequestDto {
    private String username;
    private String password;
    private String cellphone;
    private String birthday;
    private String nickname;

    @Builder
    public RequestDto(String username, String nickname, String password, String cellphone, String birthday) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.cellphone = cellphone;
        this.birthday = birthday;
    }
}
