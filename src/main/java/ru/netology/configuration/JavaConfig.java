package ru.netology.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }

    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    public PostController postController(PostService service) {
        return new PostController(service);
    }
}
