package bemo.bemo.dto;

import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostsDto {

    private String update_date;

    private String upload_date;
    private Long id;
    private String title;
    private String content;
    private String author;
    private String authorname;
    private String mvtitle;
    private String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    private String url;
    private MultipartFile file;
    private String hashtag;
    private List<Hashtags> hashtags;

    @Builder
    public PostsDto(String title, String content, String author, String authorname, String mvtitle,String url,MultipartFile file, String hashtag){
        this.title=title;
        this.content=content;
        this.author=author;
        this.authorname=authorname;
        this.mvtitle = mvtitle;
        this.url=url;
        this.file=file;
        this.hashtag = hashtag;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .authorname(authorname)
                .mvtitle(mvtitle)
                .upload_date(now)
                .update_date(now)
                .url(url)
                .hashtags(hashtags)
                .build();
    }
    public Posts toEntityWithoutHashtag(){
        return Posts.builderWithoutHashtag()
                .title(title)
                .content(content)
                .author(author)
                .authorname(authorname)
                .mvtitle(mvtitle)
                .upload_date(now)
                .update_date(now)
                .url(url)
                .buildWithoutHashtag();
    }

    public PostsDto(Posts entity){
        this.id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.authorname = entity.getAuthorname();
        this.mvtitle = entity.getMvtitle();
        this.url=entity.getUrl();
        this.hashtags = entity.getHashtags();
    }
    public void setHashtags(List<Hashtags> hashtags) {
        this.hashtags = hashtags;
    }
}