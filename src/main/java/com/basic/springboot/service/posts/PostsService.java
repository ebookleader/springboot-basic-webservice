package com.basic.springboot.service.posts;

import com.basic.springboot.domain.posts.Posts;
import com.basic.springboot.domain.posts.PostsRepository;
import com.basic.springboot.web.dto.PostsResponseDto;
import com.basic.springboot.web.dto.PostsSaveRequestDto;
import com.basic.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("There is no post. id = "+id)
        );
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("There is no post. id = "+id)
        );
        return new PostsResponseDto(entity);
    }
}
