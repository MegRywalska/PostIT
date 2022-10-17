package pl.postit.postit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.postit.postit.dto.PostCreateDTO;
import pl.postit.postit.dto.PostDTO;
import pl.postit.postit.model.Post;
import pl.postit.postit.model.Status;
import pl.postit.postit.model.User;
import pl.postit.postit.repository.PostRepository;
import pl.postit.postit.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostDTO> getPosts(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            return postRepository.findAllByCreatorPost(userOptional.get())
                    .stream()
                    .map(PostDTO::fromPost)
                    .collect(Collectors.toList());
        }

        throw new EntityNotFoundException("Unable to find user.");
    }

    public List<PostDTO> getPosts() {
        return postRepository.findAll().stream().map(PostDTO::fromPost).collect(Collectors.toList());
    }

    public PostDTO createPost(PostCreateDTO createRequest, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()) {
            Post post = Post.builder()
                    .status(Status.ORIGINAL)
                    .text(createRequest.getText())
                    .comments(new HashSet<>())
                    .creatorPost(userOptional.get())
                    .build();

            return PostDTO.fromPost(postRepository.save(post));
        }

        throw new EntityNotFoundException("Unable to find user.");
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public PostDTO updatePostById(Long id, PostCreateDTO updateInformation) {
        Optional<Post> searchedPostOptional = postRepository.findById(id);
        if (searchedPostOptional.isPresent()) {
            Post post = searchedPostOptional.get();
            post.setText(updateInformation.getText());
            post.setStatus(Status.EDIT);

            return PostDTO.fromPost(postRepository.save(post));
        }
        throw new EntityNotFoundException(("Didn't find post with id: " + id));
    }
}
