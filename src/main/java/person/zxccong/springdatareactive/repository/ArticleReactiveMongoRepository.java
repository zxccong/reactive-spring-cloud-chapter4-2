package person.zxccong.springdatareactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import person.zxccong.springdatareactive.model.Article;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Repository
public interface ArticleReactiveMongoRepository extends ReactiveMongoRepository<Article,String>, ReactiveQueryByExampleExecutor<Article> {
}
