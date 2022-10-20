package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("review")
    public String review(Model model) {
        model.addAttribute("data","hello!!");
        return "review";


    }

    @GetMapping("suggest")
    public String suggest(Model model) {
        model.addAttribute("data","hello!!");
        return "suggest";


    }
}


