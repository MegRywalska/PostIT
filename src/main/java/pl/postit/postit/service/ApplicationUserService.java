package pl.postit.postit.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.postit.postit.model.User;
import pl.postit.postit.repository.UserRepository;

import java.security.Principal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            log.info("Email found, logging user: " + email);
            return (UserDetails) userOptional.get();
        }
        throw new UsernameNotFoundException("Can't find user with this email: " + email);
    }

    public Optional<Long> getLoggedInUserId(Principal principal){
        if (principal != null){
            log.info("User logged in successfully" + principal);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
            try{
                User userDetails = (User) loadUserByUsername((String) usernamePasswordAuthenticationToken.getPrincipal());
                return Optional.of(userDetails.getId());
            }catch (UsernameNotFoundException usernameNotFoundException){
                log.info("Failed to log in");
            }
        }
        return Optional.empty();
    }
}
