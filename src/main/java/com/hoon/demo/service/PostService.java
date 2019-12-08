package com.hoon.demo.service;

import com.hoon.demo.domain.posts.Post;
import com.hoon.demo.domain.posts.PostRepository;
import com.hoon.demo.web.dto.PostResponseDto;
import com.hoon.demo.web.dto.PostSaveReqeustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveReqeustDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    public PostResponseDto findById(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostResponseDto(entity);
    }
}
