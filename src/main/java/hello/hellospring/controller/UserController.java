package hello.hellospring.controller;

import hello.hellospring.dto.UserDto;
import hello.hellospring.entity.Authentication;
import hello.hellospring.entity.User;
import hello.hellospring.repository.UserRepository;
import hello.hellospring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/error")
    public ModelAndView getUsers() {
        List<User> userList = userService.findAll();
        return new ModelAndView("/error", "users", userList);
    }

    @PostMapping("/signup")
    public Long create(@RequestBody UserDto userdto) {
        return userService.create(userdto);
    }
//    @Autowired
//    private AuthenticationRepository  authenticationRepository;
//    private UserRepository userRepository;
//
//    @GetMapping("/join")
//    public String join() {
//        return "join";
//    }
//
//    @RequestMapping(value = "/jointwo",method = RequestMethod.POST)
//    public Long jointwo( @RequestBody AuthenticationDto form) { //json 데이터를 받아온다.
//        log.info(form.toString());
//        //1.dto를 엔티티 변환
//        Authentication authentication = form.toEntity();
//        System.out.println("hi");
//        //repository에게 (db-관리-객체) 전달
//        Authentication saved = authenticationRepository.save(authentication);
//        log.info(saved.toString());
//
//        return saved.getAuth_id();
//    }
    @PostMapping("/print")
    public void print(String username) {
        User user = userService.loadUserByUsername(username);
        System.out.println(user.getUsername()+"님의 비밀번호는" +user.getPassword() + "이다");

    }

//    @PutMapping("/user/{id}")
//    public Long update(@PathVariable Long id, @RequestBody UserDto userdto) {
//        return userService.update(id, userdto);
//    }
}