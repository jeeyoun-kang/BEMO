package bemo.bemo.dto;

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
