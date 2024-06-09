package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    } //스프링 컨트롤러 안에 있는 관련 컨트롤러를 찾고 없으면 static파일(정적s)을 찾는다. 정의되어 있으므로 웰컴페이지는 우선순위가 낮다.


}
