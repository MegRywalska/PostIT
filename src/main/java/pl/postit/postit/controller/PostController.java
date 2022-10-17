package pl.postit.postit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.postit.postit.dto.PostCreateDTO;
import pl.postit.postit.dto.PostDTO;
import pl.postit.postit.service.ApplicationUserService;
import pl.postit.postit.service.PostService;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final ApplicationUserService applicationUserService;

    public List<PostDTO> getPosts(Principal principal){
        Optional<Long> userIdOptional = applicationUserService.getLoggedInUserId(principal);
        if(userIdOptional.isPresent()) {
            return postService.getPosts(userIdOptional.get());
        }

        throw new EntityNotFoundException("Not logged in.");
    }

    @GetMapping("")
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("")
    public PostDTO putPost(Principal principal, @RequestBody PostCreateDTO postDTO){
        Optional<Long> userIdOptional = applicationUserService.getLoggedInUserId(principal);
        if(userIdOptional.isPresent()) {
            return postService.createPost(postDTO, userIdOptional.get());
        }

        throw new EntityNotFoundException("Not logged in.");
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
    }

    @PatchMapping("/{id}")
    public PostDTO updatePost(@PathVariable(name = "id") Long id, @RequestBody PostCreateDTO postCreateDTO) {
        return postService.updatePostById(id, postCreateDTO);
    }

}
