package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {
    @GetMapping("/{movie_title}/review")
    public String review(Model model, @PathVariable String movie_title) {
        model.addAttribute("movie_title",movie_title);

        return "review";


    }

    @GetMapping("suggest")
    public String suggest(Model model) {
        model.addAttribute("data","hello!!");
        return "suggest";


    }
}


