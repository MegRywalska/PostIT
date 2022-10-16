package pl.postit.postit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.postit.postit.dto.CommentCreateDTO;
import pl.postit.postit.dto.CommentDTO;
import pl.postit.postit.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public CommentDTO getComment(@PathVariable(name = "id") Long id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("")
    public List<CommentDTO> getComments() {
        return commentService.getComments();
    }

    @PutMapping("")
    public CommentDTO putComment(@RequestBody CommentCreateDTO commentCreateDTO) {
        return commentService.createComment(commentCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable(name = "id") Long id) {
        commentService.deleteCommentById(id);
    }

    @PatchMapping("/{id}")
    public CommentDTO updateComment(@PathVariable(name = "id") Long id, @RequestBody CommentCreateDTO commentCreateDTO) {
        return commentService.updateCommentById(id, commentCreateDTO);
    }
}
