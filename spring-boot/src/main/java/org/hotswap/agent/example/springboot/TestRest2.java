package org.hotswap.agent.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest2 {
    @GetMapping(value = "/hello2")
    @ResponseBody
    public String hello() {
        return "hello2";
    }
}
