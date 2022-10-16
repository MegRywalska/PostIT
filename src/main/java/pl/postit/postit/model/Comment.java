package pl.postit.postit.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @CreationTimestamp
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User creatorComment;

//    @ManyToOne
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Comment comment;

}
