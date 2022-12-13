package hello.hellospring.dto;

import hello.hellospring.entity.SocialLogin;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class SocialDto {
    private int socialCode;
    private String externalId;
    private String accessToken;
    LocalDateTime now =  LocalDateTime.now();

    @Builder
    public SocialDto(int socialCode, String externalId, String accessToken) {
        this.socialCode = socialCode;
        this.externalId = externalId;
        this.accessToken = accessToken;
    }

    public SocialLogin toEntity() {
        return SocialLogin.builder()
                .socialCode(socialCode)
                .externalId(externalId)
                .accessToken(accessToken)
                .updateDate(now)
                .build();
    }
}
