package pl.postit.postit.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    private String text;

    @CreationTimestamp
    private LocalDate creationDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User creatorComment;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Comment comment;

}
