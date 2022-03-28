package person.zxccong.springdatareactive.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import person.zxccong.springdatareactive.model.Article;
import person.zxccong.springdatareactive.repository.ArticleReactiveMongoRepository;
import person.zxccong.springdatareactive.repository.ArticleReactiveRedisRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Service
public class ArticleRedisService {

    private final ArticleReactiveRedisRepository articleReactiveRedisRepository;

    ArticleRedisService(ArticleReactiveRedisRepository articleRepository) {
        this.articleReactiveRedisRepository = articleRepository;
    }

    public Mono<Article> save(Article article) {
        return articleReactiveRedisRepository.saveArticle(article);
    }

    public Mono<Article> findArticleById(String id) {

        return articleReactiveRedisRepository.findArticleById(id);
    }


    public Mono<Void> delete(String id) {
        return articleReactiveRedisRepository.deleteArticle(id);
    }

    public Flux<Article> findAllArticles() {
        return articleReactiveRedisRepository.findAllArticles();
    }

}
