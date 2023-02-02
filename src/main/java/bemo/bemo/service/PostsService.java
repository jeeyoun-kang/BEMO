package bemo.bemo.service;

import bemo.bemo.dto.PostsDto;
import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import bemo.bemo.repository.HashtagsRepository;
import bemo.bemo.repository.PostsRepository;
import bemo.bemo.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;
    private final HashtagsRepository hashtagsRepository;

    public PostsService(PostsRepository postsRepository, UserRepository userRepository, HashtagsRepository hashtagsRepository) {
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
        this.hashtagsRepository = hashtagsRepository;
    }

    @Transactional
    public Long save(PostsDto requestDto) {
        Posts posts;
        String hash = requestDto.getHashtag();
        System.out.println("해시"+hash);
        if(hash.equals("empty")) {
            posts = postsRepository.save(requestDto.toEntityWithoutHashtag());
        }
        else {
            String[] data = StringUtils.split(requestDto.getHashtag(), ",");
            data[data.length - 1] = data[data.length - 1].replaceAll(",$", "");
            System.out.println("전달값:" + data);
            List<Hashtags> hashtags = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                Hashtags hashtag = new Hashtags(data[i], requestDto.getMvtitle());
                hashtagsRepository.save(hashtag);
                hashtags.add(hashtag);
            }
            requestDto.setHashtags(hashtags);
            posts = postsRepository.save(requestDto.toEntity());
        }
        userRepository.findByUsername(requestDto.getAuthor()).addPosts(posts);
        return posts.getPost_id();
    }


    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional
    public Long update (Long id, PostsDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        posts.setUpdate_date(now);
        posts.update(requestDto.getTitle(), requestDto.getContent(),requestDto.getUrl());


        return id;
    }



    @Transactional(readOnly = true)
    public PostsDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsDto(entity);
    }


    public List<Posts> findAllDesc(){
        return postsRepository.findAllDesc(Sort.by(Sort.Direction.ASC, "post_id"));
    }
    public List<Hashtags> findHashtags(String title) {return hashtagsRepository.findByMvtitle(title);}
    public List<Posts> findByMvtitle(String mvtitle) {
        return postsRepository.findByMvtitle(mvtitle);
    }


}