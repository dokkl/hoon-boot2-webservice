package com.hoon.demo.web;

import com.hoon.demo.service.PostService;
import com.hoon.demo.web.dto.PostResponseDto;
import com.hoon.demo.web.dto.PostSaveReqeustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PutMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveReqeustDto reqeustDto) {
        return postService.save(reqeustDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto findById(@PathVariable Long id) {
        return postService.findById(id);
    }
}
