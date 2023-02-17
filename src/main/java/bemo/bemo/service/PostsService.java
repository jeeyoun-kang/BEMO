package bemo.bemo.service;

import bemo.bemo.dto.PostsDto;
import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import bemo.bemo.repository.HashtagsRepository;
import bemo.bemo.repository.PostsRepository;
import bemo.bemo.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        if(hash.equals("empty")) {
            posts = postsRepository.save(requestDto.toEntityWithoutHashtag());
        }
        else {
            List<Hashtags> hashtags = new ArrayList<>();

            String[] data = StringUtils.split(requestDto.getHashtag(), ",");
            if(data == null){
                Hashtags hashtag = new Hashtags(requestDto.getHashtag(),requestDto.getMvtitle());
                hashtagsRepository.save(hashtag);
                hashtags.add(hashtag);
            }
            else {
                for (int i = 0; i < data.length; i++) {
                    Hashtags hashtag = new Hashtags(data[i], requestDto.getMvtitle());
                    hashtagsRepository.save(hashtag);
                    hashtags.add(hashtag);
                }
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
        List<Hashtags> hashtagsList=hashtagsRepository.findByPosts(posts);
        hashtagsRepository.deleteAll(hashtagsList);
        postsRepository.delete(posts);
    }

    @Transactional
    public Long update (Long id, PostsDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Hashtags> hashtagsList=hashtagsRepository.findByPosts(posts);

        String hash = requestDto.getHashtag();

        if(hash.equals("empty")) {
            posts.updateWithoutHashtags(requestDto.getTitle(), requestDto.getContent(), requestDto.getUrl(), now);
        }
        else {
            hashtagsRepository.deleteAll(hashtagsList);

            List<Hashtags> hashtags = new ArrayList<>();

            String[] data = StringUtils.split(requestDto.getHashtag(), ",");
            if(data == null){
                Hashtags hashtag = new Hashtags(requestDto.getHashtag(),requestDto.getMvtitle());
                hashtagsRepository.save(hashtag);
                hashtags.add(hashtag);
            }
            else {
                for (int i = 0; i < data.length; i++) {
                    Hashtags hashtag = new Hashtags(data[i], requestDto.getMvtitle());
                    hashtagsRepository.save(hashtag);
                    hashtags.add(hashtag);
                }
            }
            requestDto.setHashtags(hashtags);

            posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getUrl(), requestDto.getHashtags(), now);
        }

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


    public List<Posts> findByMvtitle(List<String> mvtitle) {
        return postsRepository.findPostsByMvtitle(mvtitle);
    }



    public List<String> findMvtitleByHashtag(String hashtag) {
        return hashtagsRepository.findMvtitleByContentContaining(hashtag);
    }

    public List<String> sortHashtags() {
        List<String> contents = hashtagsRepository.findContent();
        if (contents != null) {
            List<Long> contentCount = new ArrayList<>();
            for (int i = 0; i < contents.size(); i++) {
                contentCount.add(hashtagsRepository.countByContent(contents.get(i)));
            }
            for (int i = 0; i < contents.size() - 1; i++) {
                Long tmpCount = contentCount.get(i);
                String tmpContent = contents.get(i);
                if (tmpCount < contentCount.get(i + 1)) {
                    contentCount.set(i, contentCount.get(i + 1));
                    contentCount.set(i + 1, tmpCount);
                    contents.set(i, contents.get(i + 1));
                    contents.set(i + 1, tmpContent);
                }
            }
            return contents;
        } else return null;
    }


}