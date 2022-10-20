package hello.hellospring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class MemberDto {
    private long user_no;
    private String user_name;
    private String email;
    private String password;
    private String auth;
    private int login_type;
}
