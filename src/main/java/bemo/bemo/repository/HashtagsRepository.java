package bemo.bemo.repository;

import bemo.bemo.entity.Hashtags;
import bemo.bemo.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagsRepository extends JpaRepository<Hashtags, Integer> {
    List<Hashtags> findByMvtitle(String mvtitle);
    List<Hashtags> findByPosts(Posts posts);
}
