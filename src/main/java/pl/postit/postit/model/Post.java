package pl.postit.postit.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @CreationTimestamp
    private LocalDate creationData;

    @Enumerated(EnumType.STRING)
    private Status status;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany
    private Set<Comment> comments;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User creatorPost;

}
