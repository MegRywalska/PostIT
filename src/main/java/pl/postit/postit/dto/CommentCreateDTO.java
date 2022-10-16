package pl.postit.postit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.postit.postit.model.Status;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDTO {

    private String text;
    private LocalDate creationDate = LocalDate.now();
    private Status status = Status.ORIGINAL;
}
