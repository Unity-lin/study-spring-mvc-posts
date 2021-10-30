package com.example.controller;

import com.example.dto.PostsListResponseDto;
import com.example.dto.PostsResponseDto;
import com.example.dto.PostsSaveRequestDto;
import com.example.dto.PostsUpdateRequestDto;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiContoller {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@Validated @RequestBody PostsSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        return postService.findByID(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        System.out.println(requestDto);
        System.out.println(id);
        return postService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsResponseDto> findAll () {
        return postService.findAll();
    }
}


