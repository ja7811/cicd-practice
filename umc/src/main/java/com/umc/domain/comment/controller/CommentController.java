package com.umc.domain.comment.controller;

import com.umc.common.response.ApiResponse;
import com.umc.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {
    private final CommentService commentService;

    @CrossOrigin
    @Operation(summary = "댓글 조회 API")
    @GetMapping("/posts/{postId}/comments")
    public ApiResponse<Object> findComments(@PathVariable String postId){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "댓글 작성 API")
    @PostMapping("/posts/{postId}/comments")
    public ApiResponse<Object> writeComment(@PathVariable String postId){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "댓글 삭제 API")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ApiResponse<Object> deleteComment(@PathVariable String postId, @PathVariable String commentId){
        return null;
    }

    @CrossOrigin
    @Operation(summary = "댓글 수정 API")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ApiResponse<Object> updateComment(@PathVariable String postId, @PathVariable String commentId){
        return null;
    }
}
