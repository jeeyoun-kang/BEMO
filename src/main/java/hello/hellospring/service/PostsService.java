package hello.hellospring.service;

import hello.hellospring.auth.PrincipalDetails;
import hello.hellospring.dto.PostsSaveDto;
import hello.hellospring.entity.Posts;
import hello.hellospring.entity.User;
import hello.hellospring.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    public PostsService(PostsRepository postsRepository, UserRepository userRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long save(PostsSaveDto requestDto) {
        Posts posts = postsRepository.save(requestDto.toEntity());
        userRepository.findByUsername(requestDto.getAuthor()).addPosts(posts);
        return posts.getPost_id();
    }


    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsSaveDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsSaveDto(entity);
    }


    public List<Posts> findAllDesc(){
        return postsRepository.findAllDesc(Sort.by(Sort.Direction.ASC, "post_id"));
    }

    public List<Posts> findByMvtitle(String mvtitle) {
        return postsRepository.findByMvtitle(mvtitle);
    }

}