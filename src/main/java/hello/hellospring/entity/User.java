package hello.hellospring.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
@Entity
public class User implements UserDetails {
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name="loginType", nullable = false)
    private int loginType;

    @OneToOne
    @JoinColumn(name="auth_id")
    private Authentication auth;

    @OneToOne
    @JoinColumn(name="pass_id")
    private Password password;

    @OneToOne
    @JoinColumn(name="social_id")
    private SocialLogin social;

    @Builder
    public User(String userName, int loginType, Authentication auth, Password password, SocialLogin social){
        this.userName = userName;
        this.loginType = loginType;
        this.auth = auth;
        this.password = password;
        this.social = social;
    }

    public void update(String userName, int loginType, Authentication auth, Password password, SocialLogin social){
       this.userName = userName;
       this.loginType = loginType;
       this.auth = auth;
       this.password = password;
       this.social = social;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }
    @Override
    public String getPassword() {
        return password.getPassword();
    }
    @Override
    public String getUsername() {
        return userName;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
