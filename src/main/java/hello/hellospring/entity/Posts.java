package hello.hellospring.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private String mvtitle;
    @Builder
    public Posts(String title, String content, String author, String mvtitle) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.mvtitle=mvtitle;
    }



}