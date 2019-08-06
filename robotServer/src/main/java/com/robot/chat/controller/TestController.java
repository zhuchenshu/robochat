package com.robot.chat.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("test")
@RestController
public class TestController {

    @RequestMapping()
    public String test() {
        return "hello world";
    }

    @PostMapping()
    public String chat(@RequestParam String query) {
        System.out.println(query);
        return query;
    }
}
