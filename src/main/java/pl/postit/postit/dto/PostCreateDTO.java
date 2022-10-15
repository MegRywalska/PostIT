package pl.postit.postit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.postit.postit.model.StatusPost;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO {

    private String text;
    private LocalDate creationDate = LocalDate.now();
    private StatusPost statusPost = StatusPost.ORIGINAL;

}
