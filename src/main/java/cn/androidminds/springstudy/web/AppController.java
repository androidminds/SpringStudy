package cn.androidminds.springstudy.web;


import cn.androidminds.springstudy.domain.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @RequestMapping(value="/getrest")
    public Greeting getRest() {
        Greeting greeting = new Greeting(2, "Hello");
        int i = 100/0;
        return greeting;
    }
}
