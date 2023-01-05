package hello.hellospring.dto;

import hello.hellospring.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveDto {
    private String title;
    private String content;
    private String author;

    private String mvtitle;
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
                .build();
    }
}