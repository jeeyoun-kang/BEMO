package hello.hellospring.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "social_login_id")
@Table(name="social_login")
@Entity
public class SocialLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long social_login_id;

    @Column(name = "socialCode")
    private int socialCode;

    @Column(name="external_id")
    private String externalId;

    @Column(name = "accessToken")
    private String accessToken;


    @Column(name="updateDate")
    private LocalDateTime updateDate;

    @OneToOne(mappedBy="social")
    private User user;

    @Builder
    public SocialLogin(int socialCode, String externalId, String accessToken, LocalDateTime updateDate){
        this.socialCode=socialCode;
        this.externalId=externalId;
        this.accessToken=accessToken;
        this.updateDate=updateDate;
    }

    public SocialLogin update(int socialCode, String externalId, String accessToken, LocalDateTime updateDate){
        this.socialCode=socialCode;
        this.externalId=externalId;
        this.accessToken=accessToken;
        this.updateDate=updateDate;
        return this;
    }
}
