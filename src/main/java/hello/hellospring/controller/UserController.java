package hello.hellospring.controller;

import hello.hellospring.dto.UserDto;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired(required = false)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signin")
    public String nLogin(Model model){
        return "main";
    }

    @PostMapping("/signup")
    public Long create(@RequestBody UserDto userdto) {
        return userService.create(userdto);
    }

    @PutMapping("/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserDto userdto) {
        return userService.update(id, userdto);
    }
}