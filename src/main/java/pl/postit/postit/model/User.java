package pl.postit.postit.model;

import lombok.*;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
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

    public User(String username, String password, String email, StatusAccount statusAccount,
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

