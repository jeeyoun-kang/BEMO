package hello.hellospring.repository;

import hello.hellospring.entity.Hashtags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagsRepository extends JpaRepository<Hashtags, Integer> {
}
