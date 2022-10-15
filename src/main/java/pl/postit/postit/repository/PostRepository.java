package pl.postit.postit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.postit.postit.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
