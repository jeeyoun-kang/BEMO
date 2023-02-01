package bemo.bemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HashtagController {
    @GetMapping("/hashtag")
    public String hello(Model model) {
        
        return "hashtag";


    }
}
