package hello.hellospring.dto;

import hello.hellospring.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveDto {
    private Long id;

    private String title;
    private String content;
    private String author;
    private String mvtitle;
    @Builder
    public PostsSaveDto(Long id,String title,String content,String author,String mvtitle){
        this.id = id;
        this.title=title;
        this.content=content;
        this.author=author;
        this.mvtitle = mvtitle;
    }
    public Posts toEntity(){
        return Posts.builder()
                .id(id)
                .title(title)
                .content(content)
                .author(author)
                .mvtitle(mvtitle)
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