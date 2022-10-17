package pl.postit.postit.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.postit.postit.model.User;
import pl.postit.postit.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialDatabaseEntriesConfigurer implements ApplicationListener<ContextRefreshedEvent> {
    private final static String ADMIN_USERNAME = "admin";
    private final static String ADMIN_PASSWORD = "nimda";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createUsers();
    }

    private void createUsers() {
        addUserIfNotExist(ADMIN_USERNAME, ADMIN_PASSWORD);
    }

    private void addUserIfNotExist(String username, String password) {
        if (!userRepository.existsByEmail(username)) {

            User user = User.builder()
                    .email(username)
                    .password(bCryptPasswordEncoder.encode(password))
                    .build();

            userRepository.save(user);
        }
    }
}
