package com.umc.domain.post.dto;

import com.umc.domain.board.entity.Board;
import com.umc.domain.user.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class SimplePostDTO {
    private String title;
    private Board board;
    private Member writer;
    private LocalDateTime createdAt;
}
