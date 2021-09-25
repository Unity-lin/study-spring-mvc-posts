package com.example.controller;


import com.example.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    private final PostService postService;

    public HelloWorldController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/hello2")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAllDesc());
        return "index";
    }

    @GetMapping("/post-save")
    public String postSave() { return "posts-save";}

    @GetMapping("/post-update")
    public String postUpdate() {return  "posts-update";}

}
