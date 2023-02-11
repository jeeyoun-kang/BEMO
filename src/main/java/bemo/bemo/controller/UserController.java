package bemo.bemo.controller;

import bemo.bemo.auth.PrincipalDetails;
import bemo.bemo.entity.User;
import bemo.bemo.dto.RequestDto;
import bemo.bemo.service.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class UserController {
    private final PrincipalDetailsService principalDetailsService;

    @Autowired(required = false)
    public UserController(PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
    }

    @GetMapping("/error")
    public ModelAndView getUsers() {
        List<User> userList = principalDetailsService.findAll();
        return new ModelAndView("/error", "users", userList);
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("now", now);
        return "signup";

    }

    @RequestMapping(value = "/profile")
    public String profile(Model model, @RequestParam("userinfo") String username){
        System.out.println("아이디="+username);
        PrincipalDetails principalDetails = principalDetailsService.loadPrincipalByUsername(username);
        if(principalDetails != null){
            model.addAttribute("nickname", principalDetails.getNickname());
            model.addAttribute("cellphone",principalDetails.getCellphone());
            model.addAttribute("birthday", principalDetails.getBirthday());
            model.addAttribute("Posts", principalDetails.getUser().getPosts());
        }
        return "profile";
    }
}