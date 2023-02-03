package bemo.bemo.controller;

import bemo.bemo.dto.PostsDto;
import bemo.bemo.service.S3Service;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import bemo.bemo.service.PostsService;
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
    public Long save(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file, @PathVariable String movie_title) throws IOException, ParseException {

        if (file.isEmpty()) {
            String imgPath = s3Service.getThumbnailPath("BEMO.png");
            System.out.println(imgPath);
            requestDto.setUrl(imgPath);
        }
        else {
            System.out.println(file);
            String url = s3Service.uploadFile(file);
            requestDto.setUrl(url);
        }

        return postsService.save(requestDto);
    }

    @PostMapping ("/{title}/update/posts")
    public Long update(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file, @PathVariable String title) throws IOException {
        if (file.isEmpty()) {
            String imgPath = s3Service.getThumbnailPath("BEMO.png");
            System.out.println(imgPath);
            requestDto.setUrl(imgPath);
        }
        else {
            System.out.println(file);
            String url = s3Service.uploadFile(file);
            requestDto.setUrl(url);
        }
        Long id = requestDto.getId();
        System.out.println(requestDto.getHashtags());
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
