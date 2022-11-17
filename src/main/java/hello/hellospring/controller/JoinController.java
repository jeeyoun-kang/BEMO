package hello.hellospring.controller;

import hello.hellospring.dto.AuthenticationDto;
import hello.hellospring.entity.Authentication;
import hello.hellospring.repository.AuthenticationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
public class JoinController {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @RequestMapping(value = "/jointwo",method = RequestMethod.POST)
    public Long jointwo( @RequestBody AuthenticationDto form) { //json 데이터를 받아온다.
        log.info(form.toString());
        //1.dto를 엔티티 변환
        Authentication authentication = form.toEntity();
        System.out.println("hi");
        //repository에게 (db-관리-객체) 전달
        Authentication saved = authenticationRepository.save(authentication);
        log.info(saved.toString());

        return saved.getAuth_id();
    }


}

