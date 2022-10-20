package hello.hellospring.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "social_login_id")
@Table(name="social_login")
@Entity
public class Social_login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long social_login_id;

    @Column(name = "social_code")
    private int social_code;

    @Column(name="external_id")
    private String external_id;

    @Column(name = "access_token")
    private String access_token;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="update_date")
    private Date update_date;

    @Builder
    public Social_login(int social_code, String external_id, String access_token, Date update_date){
        this.social_code=social_code;
        this.external_id=external_id;
        this.access_token=access_token;
        this.update_date=update_date;
    }

    public Social_login update(int social_code, String external_id, String access_token, Date update_date){
        this.social_code=social_code;
        this.external_id=external_id;
        this.access_token=access_token;
        this.update_date=update_date;
        return this;
    }
}
