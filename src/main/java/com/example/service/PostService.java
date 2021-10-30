package com.example.service;

import com.example.dto.PostsListResponseDto;
import com.example.dto.PostsResponseDto;
import com.example.dto.PostsSaveRequestDto;
import com.example.dto.PostsUpdateRequestDto;
import com.example.model.Posts;
import com.example.model.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
        private  final PostsRepository postsRepository;


        @Transactional
        public Long update(Long id, PostsUpdateRequestDto requestDto) {
            Posts posts = postsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

            posts.update(requestDto.getTitle(), requestDto.getContent());
            return id;
        }

        @Transactional
        public Long save(PostsSaveRequestDto requestDto){
            return postsRepository.save(requestDto.toEntity()).getId();
        }

        public List<PostsListResponseDto> findAllDesc(){
            return postsRepository.findAllDesc().stream()
                    .map(PostsListResponseDto::new)
                    .collect(Collectors.toList());
        }

    public PostsResponseDto findByID(Long id) {
            Posts entity = postsRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

            return new PostsResponseDto(entity);
    }


    public List<PostsResponseDto> findAll() {
            return postsRepository.findAll()
                    .stream()
                    .map(posts -> new PostsResponseDto(posts))
                    .collect(Collectors.toList());
    }
}

