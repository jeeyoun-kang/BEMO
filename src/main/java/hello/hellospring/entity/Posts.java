package hello.hellospring.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
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

    private String upload_date;
    private String update_date;


    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_no")
    private User user;

    @Builder
    public Posts(String title, String content, String author, String mvtitle, String upload_date, String update_date,String url) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.mvtitle=mvtitle;
        this.upload_date = upload_date;
        this.update_date = update_date;
        this.url = url;
    }
    public void update(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url=url;


    }
    public void setUser(User user) {
        this.user = user;
    }


}