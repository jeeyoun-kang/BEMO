package hello.hellospring.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

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
    private long userno;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name="login_type", nullable = false)
    private int logintype;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "authentication", joinColumns = @JoinColumn(name = "user_no"),
            inverseJoinColumns = @JoinColumn(name = "auth"))
    private Authentication auth;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "password", joinColumns = @JoinColumn(name = "user_no"),
            inverseJoinColumns = @JoinColumn(name = "password"))
    private Password password;


    @Builder
    public User(String user_name,  String password){
        this.password.updatePassword(password);
        this.username=user_name;
    }

    public User update(String user_name, int login_type){
        Assert.notNull(user_name, "user_name must be not null");
        Assert.notNull(login_type, "login_type must be not null");
        this.username=user_name;
        this.logintype=login_type;
        return this;
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
        return username;
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
