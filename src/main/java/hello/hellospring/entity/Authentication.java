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

//    @Column(name = "email", nullable = false)
//    private String email;

    @Column(name="birthday")
    private String birthday;


//    @Column(name="sex")
//    private int sex;
//
//    @Column(name="nation")
//    private int nation;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="auth_date")
    private Date auth_date;

    @OneToOne(mappedBy="auth")
    private User user;



    @Builder
    public Authentication(Long auth_id,String auth,String cell_phone,String birthday,Date auth_date){
        this.cell_phone=cell_phone;
        this.auth_id = auth_id;
        this.auth = auth;
        this.birthday=birthday;
    }

//    public Authentication update(String cell_phone){
//        return this;
//    }

    public String[] split(String s) {
        return auth.split(",");
    }
    public Authentication updateAuth(String auth){
        this.auth = auth;
        return this;
    }
}
