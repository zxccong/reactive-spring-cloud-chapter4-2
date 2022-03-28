package person.zxccong.springdatareactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zxccong
 * @date 2022/3/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "article")
public class Article {

    @Id
    private String id;
    private String title;
    private String content;
    private String author;
}
