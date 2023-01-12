package hello.hellospring.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long post_id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private String mvtitle;

    @ManyToOne
    @JoinColumn(name="user_no", nullable = false)
    private User user;

    @Builder
    public Posts(Long id,String title, String content, String author, String mvtitle) {
        this.post_id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.mvtitle=mvtitle;
        this.user = user;
    }



}