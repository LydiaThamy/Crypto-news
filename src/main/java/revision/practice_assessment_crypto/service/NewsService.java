package revision.practice_assessment_crypto.service;

import java.io.StringReader;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import revision.practice_assessment_crypto.model.Article;
import revision.practice_assessment_crypto.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepo;

    @Value("${revision.crypto.api.url}")
    private String url;

    public String getUser() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public List<Article> getArticles() {

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(url, String.class);

        List<Article> articles = Article.createList(resp.getBody());

        return articles;
    }

    public void saveArticles(List<String> savedIds, List<Article> articles) {

        // retrieve the article object from Json
        List<Article> savedArticles = articles.stream()
                .filter(a -> savedIds.contains(a.getId()))
                .collect(Collectors.toList());

        // send list of saved articles to repository
        newsRepo.persistArticles(savedArticles);

        // System.out.println(savedArticles.toString());
    }

    public JsonObject getSavedArticle(String id) {

        // retrieve redis value with user key
        String savedArticle = newsRepo.retrieveSavedArticle(id);

        if (savedArticle == null) {
            return null;
        }

        JsonReader reader = Json.createReader(new StringReader(savedArticle));
        JsonObject jObj = reader.readObject();
        
        return jObj;
    }

}
