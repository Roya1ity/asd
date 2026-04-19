package com.example.hello.controler;


import com.example.hello.dto.RequestLogin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Request:
 * Post /api/user/ http 1.1
 * Host: api.example.com
 * Content-type: Application/Json
 *
 * {
 *     name=어쩌구
 * }
 *
 * Response:
 * Http/ 1.1 200 OK
 * Content-type: Application/Json
 *
 * {
 *     json data...
 * }
 * */

@RestController
public class HelloControler {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "World")String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/login")
    public String login(String id, String pw) {

        if (id.equals("skawns521") && pw.equals("1234")) {
            return "로그인 성공";
        }
        return "로그인 실패";
    }

    @PostMapping("/login")
    public String signin(@RequestBody RequestLogin login) {
        if (login.getId().equals("skawns521") && login.getPw().equals("1234")) {
            return "로그인 성공";
        }
        return "로그인 실패";
    }
}
