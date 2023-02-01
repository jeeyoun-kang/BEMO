package hello.hellospring.controller;

import hello.hellospring.dto.PostsDto;
import hello.hellospring.service.PostsService;
import hello.hellospring.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final S3Service s3Service;

    private final PostsService postsService;

    @PostMapping("/{movie_title}/review/posts")
    public Long save(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file, @PathVariable String movie_title) throws IOException {
        String url = s3Service.uploadFile(file);

        requestDto.setUrl(url);
        return postsService.save(requestDto);
    }

    @PostMapping ("/{title}/update/posts")
    public Long update(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file, @PathVariable String title) throws IOException {
        String url = s3Service.uploadFile(file);
        requestDto.setUrl(url);
        Long id = requestDto.getId();

        return postsService.update(id,requestDto);
    }



    @DeleteMapping("/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/posts/{id}")
    public PostsDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }


}
