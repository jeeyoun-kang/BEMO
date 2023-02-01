package hello.hellospring.controller;

import hello.hellospring.auth.PrincipalDetails;
import hello.hellospring.dto.RequestDto;
import hello.hellospring.entity.User;
import hello.hellospring.service.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public String signup() {
        return "signup";
    }
    @RequestMapping(value = "/join", method=RequestMethod.POST)
    public String join(RequestDto request){
        System.out.println(request.getUsername()+request.getNickname()+request.getPassword()+request.getCellphone()+request.getBirthday());
        principalDetailsService.create(request);
        return "redirect:/";
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