package revision.practice_assessment_crypto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import revision.practice_assessment_crypto.model.Article;
import revision.practice_assessment_crypto.service.NewsService;

@Controller
public class NewsController {
    
    @Autowired
    private NewsService service;

    @GetMapping(path= {"/", "/articles"}, produces = {"text/html"} )
    public String getLatestNews(Model m){

        // populate HTML with articles
        List<Article> articles = service.getArticles();
        m.addAttribute("articles", articles);

        return "latest-news";
    }

    @PostMapping(path = "/articles")
    public String saveArticles(@RequestParam("save") List<String> savedIds, Model m) {

        // populate HTML with articles
        List<Article> articles = service.getArticles();
        m.addAttribute("articles", articles);
        
        // save articles
        service.saveArticles(savedIds, articles);
        m.addAttribute("save", new ArrayList<String>());

        return "latest-news";
    }

}
