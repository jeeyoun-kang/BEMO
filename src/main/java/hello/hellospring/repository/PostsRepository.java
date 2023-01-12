package hello.hellospring.repository;

import hello.hellospring.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    List<Posts> findByMvtitle(String mvtitle);

    @Query("select p from Posts p order by p.post_id desc")
    List<Posts> findAllDesc();
}
