package revision.practice_assessment_crypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import revision.practice_assessment_crypto.model.Article;
import revision.practice_assessment_crypto.service.NewsService;

@Controller
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping(path="/", produces = { "text/html" } )
    public String getLatestNews(Model m, HttpSession session){

        List<Article> articles = service.getArticles();
        m.addAttribute("articles", articles);
        
        return "latest-news";
    }

    @PostMapping(path = "/articles")
    public String saveArticles() {
        return "articles";
    }

}
