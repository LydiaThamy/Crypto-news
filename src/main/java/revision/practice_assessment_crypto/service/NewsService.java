package revision.practice_assessment_crypto.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import revision.practice_assessment_crypto.model.Article;
import revision.practice_assessment_crypto.repository.NewsRepository;

@Service
public class NewsService {
    
    @Autowired
    private NewsRepository newsRepo;

    @Value("${revision.crypto.api.url}")
    private String url;

    public List<Article> getArticles() {

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(url, String.class);
        
        List<Article> articles = Article.createList(resp.getBody()); // pass in the body which is a string?

        return articles;        
    }

    public List<Article> saveArticles() {
        List<Article> savedArticles = new ArrayList<>();

        
        return savedArticles;        
    }
}
