package ua.com.hdcorp.hd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.com.hdcorp.hd.repository.BaseRepository;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepository.class)
public class HolidayDecorApplication {
    public static void main(String[] args) {
        SpringApplication.run(HolidayDecorApplication.class, args);
    }
}