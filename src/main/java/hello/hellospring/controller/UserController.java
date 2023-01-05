package hello.hellospring.controller;

import hello.hellospring.dto.RequestDto;
import hello.hellospring.entity.User;
import hello.hellospring.service.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        System.out.println(request.getUsername()+request.getPassword()+request.getCellphone()+request.getBirthday());
        principalDetailsService.create(request);
        return "redirect:/";
    }


//    @PutMapping("/user/{id}")
//    public Long update(@PathVariable Long id, @RequestBody UserDto userdto) {
//        return userService.update(id, userdto);
//    }
}