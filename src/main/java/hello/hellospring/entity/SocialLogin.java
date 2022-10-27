package hello.hellospring.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "social_login_id")
@Table(name="socialLogin")
@Entity
public class SocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long socialLogin_id;

    @Column(name = "socialCode")
    private int socialCode;

    @Column(name="external_id")
    private String externalId;

    @Column(name = "accessToken")
    private String accessToken;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="updateDate")
    private Date updateDate;

    @OneToOne(mappedBy="social_login")
    private User user;

    @Builder
    public SocialLogin(int socialCode, String externalId, String accessToken, Date updateDate){
        this.socialCode=socialCode;
        this.externalId=externalId;
        this.accessToken=accessToken;
        this.updateDate=updateDate;
    }

    public SocialLogin update(int socialCode, String externalId, String accessToken, Date updateDate){
        this.socialCode=socialCode;
        this.externalId=externalId;
        this.accessToken=accessToken;
        this.updateDate=updateDate;
        return this;
    }
}
