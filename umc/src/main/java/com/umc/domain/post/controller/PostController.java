package com.umc.domain.post.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @CrossOrigin
    @Operation(summary = "게시글 조회 API")
    @GetMapping()
    public ApiResponse<Object> findPosts(@RequestParam String params) {
        return null;
    }

    @CrossOrigin
    @Operation(summary = "특정 게시글 조회 API")
    @GetMapping("/{postId}")
    public ApiResponse<Object> findPost(@PathVariable String postId){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "특정 게시글 삭제 API")
    @DeleteMapping("/{postId}")
    public ApiResponse<Object> deleteById(@PathVariable String postId){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "게시글 생성 API")
    @PostMapping()
    public ApiResponse<Object> postPost(){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "게시글 수정 API")
    @PutMapping()
    public ApiResponse<Object> updatePost(){
        return null;
    }
}
