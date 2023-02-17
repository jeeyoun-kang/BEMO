package bemo.bemo.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
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
    private String authorname;
    private String mvtitle;
    private String upload_date;
    private String update_date;
    private String url;

    private String mvurl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_no")
    private User user;

    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private List<Hashtags> hashtags = new ArrayList<>();

    @Builder
    public Posts(String title, String content, String author, String authorname, String mvtitle, String upload_date, String update_date,String url,String mvurl,List<Hashtags> hashtags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorname = authorname;
        this.mvtitle=mvtitle;
        this.upload_date = upload_date;
        this.update_date = update_date;
        this.url = url;
        this.mvurl=mvurl;
        for(int i = 0; i<hashtags.size(); i++) {
            addHashtags(hashtags.get(i));
        }
    }
    @Builder(builderMethodName = "builderWithoutHashtag", buildMethodName = "buildWithoutHashtag")
    public Posts(String title, String content, String author, String authorname, String mvtitle, String upload_date, String update_date,String url,String mvurl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorname = authorname;
        this.mvtitle=mvtitle;
        this.upload_date = upload_date;
        this.update_date = update_date;
        this.url = url;
        this.mvurl=mvurl;
    }
    public void update(String title, String content, String url,List<Hashtags> hashtags, String update_date) {
        this.title = title;
        this.content = content;
        this.url=url;
        this.update_date = update_date;
        for(int i = 0; i<hashtags.size(); i++) {
            addHashtags(hashtags.get(i));
        }
    }
    public void updateWithoutHashtags(String title, String content, String url, String update_date) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.update_date = update_date;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void addHashtags(Hashtags hashtags){
        this.hashtags.add(hashtags);
        hashtags.setPost(this);
    }



}