package hello.hellospring.repository;

import hello.hellospring.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    //@Query("select p from Posts p order by p.id desc")
    //List<Posts> findAllDesc();
    List<Posts> findByMvtitle(String mvtitle);
}
