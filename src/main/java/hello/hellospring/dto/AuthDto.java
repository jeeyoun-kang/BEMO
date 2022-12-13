package hello.hellospring.dto;

import hello.hellospring.entity.Authentication;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AuthDto {
    private String cell_phone;
    private String birthday;
    LocalDateTime now =  LocalDateTime.now();
    @Builder
    public AuthDto(String cell_phone, String birthday) {
        this.cell_phone = cell_phone;
        this.birthday = birthday;
    }

    public Authentication toEntity() {
        return Authentication.builder()
                .role(Authentication.Role.ROLE_USER)
                .cell_phone(cell_phone)
                .birthday(birthday)
                .auth_date(now)
                .build();
    }
    public Authentication toEntity(String cell_phone, String birthday) {
        return Authentication.builder()
                .role(Authentication.Role.ROLE_USER)
                .cell_phone(cell_phone)
                .birthday(birthday)
                .auth_date(now)
                .build();
    }
}
