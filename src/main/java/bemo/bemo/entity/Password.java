package bemo.bemo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "pass_id")
@Table(name="password")
@Entity
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_id")
    private long pass_id;

    @Column(name="password")
    private String password;

    @Column(name="update_date")
    private LocalDateTime update_date;

    @OneToOne(mappedBy="pass")
    private User user;

    @Builder
    public Password(String password, LocalDateTime update_date){
        this.password=password;
        this.update_date = update_date;
    }
    public Password updatePassword(String password) {
        this.password = password;
        return this;
    }
    public Password update(String password, LocalDateTime update_date){
        this.password=password;
        this.update_date=update_date;
        return this;
    }
}
