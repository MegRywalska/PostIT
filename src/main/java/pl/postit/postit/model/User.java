package pl.postit.postit.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    private String email;
//    private LocalDate dateOfBirth;

//    @OneToMany(fetch= FetchType.EAGER)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<ApplicationUserRole> roles;

    @Enumerated(EnumType.STRING)
    private StatusAccount statusAccount;

    @OneToMany(mappedBy = "creatorPost")
    private Set<Post> posts;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "creatorComment")
    private Set<Comment> comments;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    private Set<User> observedUser;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany (mappedBy = "observedUser")
    private  Set<User> observedByUser;

    public User(String username, String password, String email, LocalDate dateOfBirth, StatusAccount statusAccount,
                Set<Post> posts, Set<Comment> comments, Set<User> observedUser, Set<User> observedByUser) {
        this.username = username;
        this.password = password;
        this.email=email;
        this.statusAccount = statusAccount;
        this.posts = posts;
        this.comments = comments;
        this.observedUser = observedUser;
        this.observedByUser = observedByUser;
    }
}
