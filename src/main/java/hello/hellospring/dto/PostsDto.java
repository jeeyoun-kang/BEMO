package hello.hellospring.dto;

import hello.hellospring.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String mvtitle;
    private String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private String url;

    private MultipartFile file;

    @Builder
    public PostsDto(String title, String content, String author, String mvtitle,String url,MultipartFile file){
        this.title=title;
        this.content=content;
        this.author=author;
        this.mvtitle = mvtitle;
        this.url=url;
        this.file=file;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .mvtitle(mvtitle)
                .upload_date(now)
                .update_date(now)
                .url(url)
                .build();
    }

    public PostsDto(Posts entity){
        this.id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.mvtitle = entity.getMvtitle();
        this.url=entity.getUrl();
    }



}