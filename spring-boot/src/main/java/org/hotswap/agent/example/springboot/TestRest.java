package org.hotswap.agent.example.springboot;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRest {
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
