package hello.hellospring.controller;

import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final UserService userService;

    @Autowired(required = false)
    public MemberController(UserService userService) {
        this.userService = userService;
    }
}