package pl.postit.postit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.postit.postit.model.Comment;
import pl.postit.postit.model.Status;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String text;
    private LocalDate creationDate;
    private Status status;
    private String creatorComment;

    public static CommentDTO fromComment(Comment comment){
        return CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .creationDate(comment.getCreationDate())
                .status(comment.getStatus())
                .creatorComment(comment.getCreatorComment()!= null ? comment.getCreatorComment().getUsername() : "Unknown")
                .build();
    }
}
