package pl.postit.postit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.postit.postit.dto.PostCreateDTO;
import pl.postit.postit.dto.PostDTO;
import pl.postit.postit.model.Post;
import pl.postit.postit.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDTO getPostById(Long id) {
        return PostDTO.fromPost(postRepository.getReferenceById(id));
    }

    public List<PostDTO> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(PostDTO::fromPost)
                .collect(Collectors.toList());
    }

    public  PostDTO createPost(PostCreateDTO postCreate){
        Post post = Post.builder().text(postCreate.getText()).build();
        return PostDTO.fromPost(postRepository.save(post));
    }

    public void deletePostById(Long id){
        postRepository.deleteById(id);
    }

    public PostDTO updatePostById (Long id, PostCreateDTO updateInformation){
        Optional<Post> searchedPostOptional = postRepository.findById(id);
        if (searchedPostOptional.isPresent()){
            Post post = searchedPostOptional.get();
            post.setText(updateInformation.getText());

            return PostDTO.fromPost(postRepository.save(post));
        }
        throw  new EntityNotFoundException(("Didn't find post with id: " + id));

    }


}
