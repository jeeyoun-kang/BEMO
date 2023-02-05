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

import javax.websocket.server.PathParam;

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

    @RequestMapping("/modify/{id}")
    public String update(Model model, @PathVariable Long id) {

        model.addAttribute("Posts", postsService.findById(id));
        model.addAttribute("update_id",id);
        //model.addAttribute("id",id); //id는 post_id와 같음
        return "update";
    }
}


