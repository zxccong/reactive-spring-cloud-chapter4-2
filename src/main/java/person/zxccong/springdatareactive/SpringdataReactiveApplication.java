package person.zxccong.springdatareactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import person.zxccong.springdatareactive.model.Article;

@SpringBootApplication
//@EnableReactiveMongoRepositories
public class SpringdataReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataReactiveApplication.class, args);
    }

    @Bean
    ReactiveRedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
    }

    @Bean
    ReactiveRedisTemplate<String, Article> redisOperrations(ReactiveRedisConnectionFactory factory) {

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Article.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Article> builder = RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, Article> context = builder.value(jackson2JsonRedisSerializer).build();
        return new ReactiveRedisTemplate<>(factory, context);
    }



}
