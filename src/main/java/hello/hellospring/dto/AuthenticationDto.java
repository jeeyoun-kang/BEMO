package hello.hellospring.dto;

import hello.hellospring.entity.Authentication;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.Assert;


import java.util.Date;
import java.util.PrimitiveIterator;

@Data //생성자(디폴트,All),게터,세터,toString 등 다 만들어준다.
@ToString
public class AuthenticationDto {
    private Long auth_id;
    private String cell_phone;
    private String auth;
    private String birthday;


    public AuthenticationDto(Long auth_id,String auth,String cell_phone,String birthday){

        this.cell_phone=cell_phone;
        this.auth_id = auth_id;
        this.auth = auth;
        this.birthday=birthday;

    }


//빌더 패턴으로 객체 생성, 생성자의 변형
    public Authentication toEntity() {
        return Authentication.builder()
                .auth_id(auth_id)
                .cell_phone(cell_phone)
                .auth(auth)
                .birthday(birthday)
                .build();
    }
}
