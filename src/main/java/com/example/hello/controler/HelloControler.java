package com.example.hello.controler;


import com.example.hello.dto.RequestLogin;
import com.example.hello.dto.ResponseUser;
import org.springframework.http.HttpStatus;
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
    //curl -i -X POST "http://localhost:8090/login" -H "Content-type: application/json" -d "{""id"":""skawns521"",""pw"":""1234""}"
    //curl -i -X POST "http://localhost:8090/login" -H "Content-type: application/json" -d "{\"id\":\"skawns521\",\"pw\":\"1234\"}"

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (id==1) {
            ResponseUser user = new ResponseUser("1","페이커","faker_T1@naver.com");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.notFound().build();
    }
}
