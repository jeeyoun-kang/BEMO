package hello.hellospring.controller;
import hello.hellospring.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String review(Model model, @PathVariable String movie_title) {
        model.addAttribute("movie_title",movie_title);

        return "review";


    }

    @GetMapping("suggest")
    public String suggest(Model model) {
        model.addAttribute("data","hello!!");
        return "suggest";
    }

    @RequestMapping("/comments/{id}")
    public String comments(Model model, @PathVariable Long id){
        model.addAttribute("Posts",postsService.findById(id));

        return "comments";
    }
}


