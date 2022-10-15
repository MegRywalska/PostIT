package pl.postit.postit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.postit.postit.model.Post;
import pl.postit.postit.model.StatusPost;
import pl.postit.postit.model.User;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private String text;
    private LocalDate creationDate;
    private StatusPost statusPost;
    private int numberOfComments;
    private String creatorPost;

    public static PostDTO fromPost(Post post){
        return PostDTO.builder()
                .id(post.getId())
                .text(post.getText())
                .creationDate(post.getCreationData())
                .statusPost(post.getStatusPost())
                .numberOfComments(post.getComments().size())
                .creatorPost(post.getCreatorPost()!= null ? post.getCreatorPost().getUsername() : "Unknown")
                .build();
    }
}
