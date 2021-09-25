package com.example.service;

import com.example.dto.PostsListResponseDto;
import com.example.dto.PostsSaveRequestDto;
import com.example.model.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
        private  final PostsRepository postsRepository;

        @Transactional
        public Long save(PostsSaveRequestDto requestDto){
            return postsRepository.save(requestDto.toEntity()).getId();
        }

        public List<PostsListResponseDto> findAllDesc(){
            return postsRepository.findAllDesc().stream()
                    .map(PostsListResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

