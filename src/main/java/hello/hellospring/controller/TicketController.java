package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketController {

    @GetMapping("ticket")
    public String ticket(Model model) {
        model.addAttribute("data","ticket");
        return "ticket";


    }


}
