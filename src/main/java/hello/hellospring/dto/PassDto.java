package hello.hellospring.dto;

import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.Password;
import hello.hellospring.entity.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class PassDto {
    private String password;
    LocalDateTime now =  LocalDateTime.now();

    @Builder
    public PassDto(String password) {
        this.password = password;
    }

    public Password toEntity() {
        return Password.builder()
                .password(password)
                .update_date(now)
                .build();
    }
    public Password toEntity(String password) {
        return Password.builder()
                .password(password)
                .update_date(now)
                .build();
    }
}
