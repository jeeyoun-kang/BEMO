package hello.hellospring.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "auth")
    private String auth;

    @Column(name="cell_phone", nullable = false)
    private String cell_phone;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name="birthday")
    private String birthday;

    @Column(name="sex")
    private int sex;

    @Column(name="nation")
    private int nation;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="auth_date")
    private Date auth_date;

    @OneToOne(mappedBy="authentication")
    private User user;

    @Builder
    public Authentication(String cell_phone, String email){
        Assert.notNull(cell_phone, "cell_phone must be not null");
        Assert.notNull(email, "email must be not null");
        this.cell_phone=cell_phone;
        this.email=email;
    }

    public Authentication update(String cell_phone, String email){
        Assert.notNull(cell_phone, "cell_phone must be not null");
        Assert.notNull(email, "email must be not null");
        this.cell_phone=cell_phone;
        this.email=email;
        return this;
    }

    public String[] split(String s) {
        return auth.split(",");
    }
    public Authentication updateAuth(String auth){
        this.auth = auth;
        return this;
    }
}
