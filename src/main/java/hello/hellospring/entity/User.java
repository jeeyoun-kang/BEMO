package hello.hellospring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
@Entity
public class User {
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name="loginType", nullable = false)
    private int loginType;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @OneToOne
    @JoinColumn(name="auth_id")
    private Authentication auth;


    @OneToOne
    @JoinColumn(name="pass_id")
    private Password pass;

    @OneToOne
    @JoinColumn(name="social_login_id")
    private SocialLogin social;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Posts> posts=new ArrayList<>();

    @Builder
    public User(String username, int loginType, String nickname, Authentication auth, Password pass, SocialLogin social){
        this.username = username;
        this.loginType = loginType;
        this.nickname = nickname;
        this.auth = auth;
        this.pass = pass;
        this.social = social;
    }
    public User(User user) {
        this.username = user.username;
        this.loginType = user.loginType;
        this.nickname = user.nickname;
        this.auth = user.auth;
        this.pass = user.pass;
        this.social = user.social;
    }

    public void update(String username, int loginType, String nickname, Authentication auth, Password password, SocialLogin social){
        this.username = username;
        this.loginType = loginType;
        this.nickname = nickname;
        this.auth = auth;
        this.pass = password;
        this.social = social;
    }

}
