package bemo.bemo.repository;

import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashtagsRepository extends JpaRepository<Hashtags, Integer> {
    List<Hashtags> findByMvtitle(String mvtitle);
    List<Hashtags> findByPosts(Posts posts);

    @Query("select h.mvtitle from Hashtags h where h.content like %:content%")
    List<String> findMvtitleByContentContaining(String content);
}
