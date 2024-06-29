package com.umc.domain.post.service;

import com.umc.common.response.ApiResponse;
import com.umc.common.response.status.SuccessCode;
import com.umc.domain.board.entity.Board;
import com.umc.domain.board.repository.BoardRepository;
import com.umc.domain.post.dto.PostImageDTO;
import com.umc.domain.post.dto.PostRequestDTO;
import com.umc.domain.post.dto.PostResponseDTO;
import com.umc.domain.post.dto.SimplePostResponseDTO;
import com.umc.domain.post.entity.Post;
import com.umc.domain.post.entity.PostImage;
import com.umc.domain.post.repository.PostRepository;
import com.umc.domain.user.entity.Member;
import com.umc.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final PostImageService postImageService;

    @Transactional
    public ApiResponse<List<SimplePostResponseDTO>> findAll() {
        List<Post> posts = postRepository.findAll();

        List<SimplePostResponseDTO> postDTO = posts.stream().map(
                    post -> SimplePostResponseDTO.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .boardId(post.getBoard().getId())
                        .writerNickname(post.getWriter().getNickname())
                        .createdAt(post.getCreatedAt())
                        .modifiedAt(post.getModifiedAt())
                        .build()
                ).toList();
        return ApiResponse.of(SuccessCode._OK, postDTO);
    }

    public ApiResponse<PostResponseDTO> find(String postId) {
        Post post = postRepository.findById(Long.getLong(postId)).orElseThrow();

        return ApiResponse.of(SuccessCode._OK, PostResponseDTO.builder()
                .id(Long.getLong(postId))
                .title(post.getTitle())
                .content(post.getContent())
                .writerNickname(post.getWriter().getNickname())
                .boardId(post.getBoard().getId())
                .images(
                            post.getImages().stream().map( image->
                                    PostImageDTO.builder()
                                            .originalFilename(image.getOriginalFilename())
                                            .storedFilename(image.getStoredFilename())
                                            .build()
                            ).toList()
                )
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build()
        );
    }

    public ApiResponse<String> delete(String postId) {
        Post target = postRepository.findById(Long.getLong(postId)).orElseThrow();
        postRepository.delete(target);

        return ApiResponse.of(SuccessCode._OK, "삭제되었습니다.");
    }

    public ApiResponse<Object> save(String boardId, PostRequestDTO postRequest, MultipartFile[] imageFiles) {
        Member writer = memberRepository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName()
        ).orElseThrow(); // 되나?
        Board board = boardRepository.findById(Long.getLong(boardId)).orElseThrow();
        // Post 엔티티 생성
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .writer(writer)
                .board(board)
                .build();
        // 이미지 저장
        List<PostImage> images = postImageService.save(imageFiles, post);
        post.setImages(images);

        postRepository.save(post);
        return ApiResponse.of(SuccessCode._OK, "저장되었습니다");
    }

    public ApiResponse<Object> update(String postId, PostRequestDTO postRequest, MultipartFile[] imageFiles) {
        Post target = postRepository.findById(Long.getLong(postId)).orElseThrow();
        target.setTitle(postRequest.getTitle());
        target.setContent(postRequest.getContent());

        if(!Objects.isNull(imageFiles)) {
            List<PostImage> images = postImageService.update(imageFiles, target);
            target.setImages(images);
        }

        postRepository.save(target);
        return ApiResponse.of(SuccessCode._OK, "수정되었습니다");
    }
}
