package bemo.bemo.repository;

import bemo.bemo.entity.Posts;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findByMvtitle(String mvtitle);

    @Query("select p from Posts p order by p.post_id desc")
    List<Posts> findAllDesc(Sort post_id);
}
