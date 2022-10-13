package pl.postit.postit.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @CreationTimestamp
    private LocalDate creationData;

    @Enumerated(EnumType.STRING)
    private StatusPost statusPost;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private User creatorPost;

}
