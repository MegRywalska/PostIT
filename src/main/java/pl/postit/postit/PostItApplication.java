package pl.postit.postit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PostItApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostItApplication.class, args);
    }

}
