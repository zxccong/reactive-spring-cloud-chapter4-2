package person.zxccong.springdatareactive.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import person.zxccong.springdatareactive.model.Article;

import java.util.UUID;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Component
public class InitDatabase {

    @Bean
    CommandLineRunner init(MongoOperations operations) {
        return args -> {
            operations.dropCollection(Article.class);

            operations.insert(new Article(UUID.randomUUID().toString(), "title1", "content1", "author1"));
            operations.insert(new Article(UUID.randomUUID().toString(), "title2", "content2", "author2"));
            operations.findAll(Article.class).forEach(acticle ->{
                System.out.println(acticle.toString());});

        };
    }
}
