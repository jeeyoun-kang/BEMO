package hello.hellospring.entity;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "auth_id")
@Table(name="authentication")
@Entity
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private long auth_id;

    @Column(name = "role")
    private Role role;

    @Column(name="cell_phone", nullable = false)
    private String cell_phone;

    @Column(name="birthday")
    private String birthday;

    @Column(name="auth_date")
    private LocalDateTime auth_date;

    @OneToOne(mappedBy="auth")
    private User user;

    @Builder
    public Authentication(Role role,String cell_phone,String birthday,LocalDateTime auth_date){
        this.cell_phone=cell_phone;
        this.role = role;
        this.birthday=birthday;
        this.auth_date = auth_date;
    }
    public Authentication update(String cell_phone, String birthday){
        this.cell_phone = cell_phone;
        this.birthday = birthday;
        return this;
    }

    public enum Role {
        ROLE_USER,
        ROLE_ADMIN
    }
}
