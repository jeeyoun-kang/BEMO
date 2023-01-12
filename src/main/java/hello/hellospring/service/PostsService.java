package hello.hellospring.service;

import hello.hellospring.auth.PrincipalDetails;
import hello.hellospring.dto.PostsResponseDto;
import hello.hellospring.dto.PostsSaveDto;
import hello.hellospring.entity.Posts;
import hello.hellospring.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final PrincipalDetailsService principalDetailsService;

    public PostsService(PostsRepository postsRepository, PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostsSaveDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getPost_id();
    }

    @Transactional
    public void setUser(PostsSaveDto requestDto) {
        PrincipalDetails principalDetails = principalDetailsService.loadUserByUsername(requestDto.getAuthor());

//        postsRepository.save()
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


    public List<Posts> findAllDesc(){
        return postsRepository.findAllDesc();
    }


    public List<Posts> findByMvtitle(String mvtitle) {
        return postsRepository.findByMvtitle(mvtitle);
    }



}