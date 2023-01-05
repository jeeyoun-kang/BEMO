package hello.hellospring.dto;

import hello.hellospring.entity.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;

    private String mvtitle;


    public PostsListResponseDto(Posts entity) {
        id = entity.getId();
        title = entity.getTitle();
        author = entity.getAuthor();
        mvtitle = entity.getMvtitle();

    }
}