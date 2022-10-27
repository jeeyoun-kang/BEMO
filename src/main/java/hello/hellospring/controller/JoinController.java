package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {
    @RequestMapping("join")
    public String join(Model model) {
//        model.addAttribute("data","hello!!");
        return "join";


    }
}

