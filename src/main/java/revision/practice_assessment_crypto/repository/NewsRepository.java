package revision.practice_assessment_crypto.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import revision.practice_assessment_crypto.model.Article;

@Repository
public class NewsRepository {

    @Autowired
    @Qualifier("crypto")
    private RedisTemplate<String, Object> template;

    public void persistArticles(List<Article> savedArticles) {
        // saving each article according to its ID
        savedArticles.stream()
                .forEach(a -> template.opsForValue()
                        .set(a.getId(), Article.toJson(a)
                                .toString()));
    }

    public String retrieveSavedArticle(String id) {
        
        // get saved articles with key
        return (String) template.opsForValue().get(id);
    }

}
