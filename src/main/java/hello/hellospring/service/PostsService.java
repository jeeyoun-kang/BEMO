package hello.hellospring.service;

import hello.hellospring.dto.PostsResponseDto;
import hello.hellospring.dto.PostsSaveDto;
import hello.hellospring.entity.Posts;
import hello.hellospring.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }


//    public List<Posts> findAllDesc(){
//        return postsRepository.findAllDesc();
//    }


    public List<Posts> findByMvtitle(String mvtitle) {
        return postsRepository.findByMvtitle(mvtitle);
    }



}