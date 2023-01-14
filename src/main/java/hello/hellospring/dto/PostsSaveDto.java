package hello.hellospring.dto;

import hello.hellospring.entity.Posts;
import hello.hellospring.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PostsSaveDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String mvtitle;
    private String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    @Builder
    public PostsSaveDto(String title,String content,String author,String mvtitle){
        this.title=title;
        this.content=content;
        this.author=author;
        this.mvtitle = mvtitle;
    }
    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .mvtitle(mvtitle)
                .upload_date(now)
                .update_date(now)
                .build();
    }

    public PostsSaveDto(Posts entity){
        this.id = entity.getPost_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.mvtitle = entity.getMvtitle();
    }
}