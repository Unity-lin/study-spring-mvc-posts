package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/hello2")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/post-save")
    public String postSave() { return "posts-save";}

    @GetMapping("/post-update")
    public String postUpdate() {return  "posts-update";}

}
