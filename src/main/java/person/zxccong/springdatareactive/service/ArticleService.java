package person.zxccong.springdatareactive.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import person.zxccong.springdatareactive.model.Article;
import person.zxccong.springdatareactive.repository.ArticleReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Service
public class ArticleService {

    private final ArticleReactiveMongoRepository articleReactiveMongoRepository;

    ArticleService(ArticleReactiveMongoRepository articleReactiveMongoRepository) {
        this.articleReactiveMongoRepository = articleReactiveMongoRepository;
    }

    public Mono<Article> save(Article article) {
        return articleReactiveMongoRepository.save(article);
    }

    public Mono<Article> findOne(String id) {

        return articleReactiveMongoRepository.findById(id);
    }

    public Flux<Article> findAll() {
        return articleReactiveMongoRepository.findAll();
    }

    public Mono<Void> delete(String id) {
        return articleReactiveMongoRepository.deleteById(id);
    }

    public Flux<Article> findByAuthor(String author) {
        final Article article = new Article();
        article.setAuthor(author);

        final ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase().withMatcher(author, startsWith()).withIncludeNullValues();
        final  Example<Article> example = Example.of(article, exampleMatcher);
        final Flux<Article> articles = articleReactiveMongoRepository.findAll(example).log("findByAuthor");
        return articles;
    }

}
