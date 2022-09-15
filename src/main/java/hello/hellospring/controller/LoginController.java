package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @PostMapping(value = "/signin")
    public String checkId(@RequestParam("id") String id, @RequestParam("passwd") String passwd, Model model){
        // id passwd를 db에서 비교 -> 맞으면 main으로 보내 ( 틀리면 wrong 하나 만들어서 거기로 보내 )
        return "main";
    }
    // 네이버 api관련해서도 만들어야해
}