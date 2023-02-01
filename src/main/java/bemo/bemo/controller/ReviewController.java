package bemo.bemo.controller;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.dto.PostsDto;
import bemo.bemo.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {
    @Autowired
    PostsService postsService;

    @GetMapping("/{movie_title}/review")
    public String review(Model model, @PathVariable String movie_title, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) {
            model.addAttribute("userinfo", principalDetails.getUsername());
            model.addAttribute("nickname", principalDetails.getNickname());
        }
        model.addAttribute("movie_title", movie_title);

        return "review";
    }

    @GetMapping("suggest")
    public String suggest(Model model) {
        model.addAttribute("data", "hello!!");
        return "suggest";
    }

    @RequestMapping("/comments/{id}")
    public String comments(Model model, @PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        PostsDto postsSaveDto = postsService.findById(id);
        model.addAttribute("Posts", postsService.findById(id)); //id값을 기준으로 한 post 데이터들
        if (principalDetails != null) { // 본인이면 수정 가능
            if (postsSaveDto.getAuthor().equals(principalDetails.getUsername())) model.addAttribute("writer", true);
        }
        model.addAttribute("id",id);
        return "comments";
    }

    @RequestMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {

        model.addAttribute("Posts", postsService.findById(id));
        model.addAttribute("update_id",id);
        //model.addAttribute("id",id); //id는 post_id와 같음
        return "update";
    }
}


