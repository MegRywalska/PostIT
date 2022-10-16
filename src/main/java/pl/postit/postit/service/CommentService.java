package pl.postit.postit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.postit.postit.dto.CommentCreateDTO;
import pl.postit.postit.dto.CommentDTO;
import pl.postit.postit.model.Comment;
import pl.postit.postit.model.Status;
import pl.postit.postit.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDTO getCommentById(Long id) {
        return CommentDTO.fromComment(commentRepository.getReferenceById(id));
    }

    public List<CommentDTO> getComments() {
        return commentRepository.findAll().stream().map(CommentDTO::fromComment).collect(Collectors.toList());
    }

    public CommentDTO createComment(CommentCreateDTO commentCreateDTO) {
        Comment comment = Comment.builder().text(commentCreateDTO.getText()).build();
        return CommentDTO.fromComment(commentRepository.save(comment));
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public CommentDTO updateCommentById(Long id, CommentCreateDTO updateInformation) {
        Optional<Comment> searchedCommentOptional = commentRepository.findById(id);

        if (searchedCommentOptional.isPresent()) {
            Comment comment = searchedCommentOptional.get();
            comment.setText(updateInformation.getText());
            comment.setStatus(Status.EDIT);

            return CommentDTO.fromComment(commentRepository.save(comment));
        }
        throw new EntityNotFoundException("Didn't find comment with id: " + id);
    }
}
