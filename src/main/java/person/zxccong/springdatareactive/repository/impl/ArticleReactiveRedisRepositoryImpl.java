package person.zxccong.springdatareactive.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import person.zxccong.springdatareactive.model.Article;
import person.zxccong.springdatareactive.repository.ArticleReactiveRedisRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zxccong
 * @date 2022/3/28
 */
public class ArticleReactiveRedisRepositoryImpl implements ArticleReactiveRedisRepository {

    @Autowired
    private ReactiveRedisTemplate<String, Article> reactiveRedisTemplate;

    private static final String HASH_NAME = "Article:";

    @Override
    public Mono<Boolean> saveArticle(Article article) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + article.getId(), article);
    }

    @Override
    public Mono<Boolean> updateArticle(Article article) {
        return reactiveRedisTemplate.opsForValue().set(HASH_NAME + article.getId(), article);
    }

    @Override
    public Mono<Boolean> deleteArticle(String articleId) {
        return reactiveRedisTemplate.opsForValue().delete(HASH_NAME + articleId);
    }

    @Override
    public Mono<Article> findArticleById(String articleId) {
        return reactiveRedisTemplate.opsForValue().get(HASH_NAME + articleId);
    }

    @Override
    public Flux<Article> findAllArticles() {
        return reactiveRedisTemplate.keys(HASH_NAME + "*").flatMap((String key) -> {
            Mono<Article> mono = reactiveRedisTemplate.opsForValue().get(key);
            return mono;
        });
    }
}
