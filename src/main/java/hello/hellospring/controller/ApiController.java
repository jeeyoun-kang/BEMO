package hello.hellospring.controller;

import hello.hellospring.dto.PostsResponseDto;
import hello.hellospring.dto.PostsSaveDto;
import hello.hellospring.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final PostsService postsService;

    @PostMapping("/{movie_title}/review/posts")
    public Long save(@RequestBody PostsSaveDto requestDto, @PathVariable String movie_title) {
        return postsService.save(requestDto);
    }



    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}