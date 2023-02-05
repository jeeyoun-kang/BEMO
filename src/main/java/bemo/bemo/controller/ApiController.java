package bemo.bemo.controller;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.dto.PostsDto;
import bemo.bemo.service.S3Service;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import bemo.bemo.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ApiController {
    private final S3Service s3Service;

    private final PostsService postsService;

    @GetMapping("/users/{id}")
    public ModelAndView comments(Model model, @PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("comments");
        modelAndView.addObject("Posts", postsService.findById(id));
        modelAndView.addObject("id",id);
        PostsDto postsSaveDto = postsService.findById(id);

        if (principalDetails != null) { // 본인이면 수정 가능
            if (postsSaveDto.getAuthor().equals(principalDetails.getUsername())) model.addAttribute("writer", true);
        }
        return modelAndView;
    }

    @PostMapping("/users/")
    public Long save(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file) throws IOException, ParseException {

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

    @PutMapping ("/users/{id}")
    public Long update(@RequestPart(value = "key") PostsDto requestDto, @RequestPart(value = "file") MultipartFile file,@PathVariable Long id) throws IOException {
        if (file.isEmpty()) {
            String imgPath = s3Service.getThumbnailPath("BEMO.png");
            requestDto.setUrl(imgPath);
        }
        else {
            System.out.println(file);
            String url = s3Service.uploadFile(file);
            requestDto.setUrl(url);
        }
        System.out.println(id);
        requestDto.setId(id);
        return postsService.update(id,requestDto);
    }



    @DeleteMapping("/users/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }


}
