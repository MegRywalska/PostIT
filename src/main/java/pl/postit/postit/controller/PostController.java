package pl.postit.postit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.postit.postit.dto.PostCreateDTO;
import pl.postit.postit.dto.PostDTO;
import pl.postit.postit.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable(name = "id") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("")
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("")
    public PostDTO putPost(@RequestBody PostCreateDTO postCreateDTO) {
        return postService.createPost(postCreateDTO);
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
