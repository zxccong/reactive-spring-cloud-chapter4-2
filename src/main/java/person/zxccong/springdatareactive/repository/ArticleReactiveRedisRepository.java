package person.zxccong.springdatareactive.repository;

import org.springframework.stereotype.Repository;
import person.zxccong.springdatareactive.model.Article;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Repository
public interface ArticleReactiveRedisRepository {

    Mono<Boolean> saveArticle(Article article);

    Mono<Boolean> updateArticle(Article article);

    Mono<Boolean> deleteArticle(String articleId);

    Mono<Article> findArticleById(String articleId);

    Flux<Article> findAllArticles();
}
