package bemo.bemo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "hashtags")
public class Hashtags {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hashtag_id")
    private Long hashtag_id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String mvtitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Posts posts;

    @Builder
    public Hashtags(String content, String mvtitle) {
        this.content = content;
        this.mvtitle = mvtitle;
    }
    public Hashtags update(String content, String mvtitle){
        this.content = content;
        this.mvtitle = mvtitle;
        return this;
    }
    public void setPost(Posts post) {
        this.posts = post;
    }
}
