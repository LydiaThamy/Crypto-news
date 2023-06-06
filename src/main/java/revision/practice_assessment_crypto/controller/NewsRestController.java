package revision.practice_assessment_crypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import revision.practice_assessment_crypto.service.NewsService;

@RestController
@RequestMapping(path = "/news/")
public class NewsRestController {

    @Autowired
    NewsService service;
    
    @GetMapping(path = "{id}", produces = "application/json")
    public ResponseEntity<String> showSavedArticle(@PathVariable(name="id") String id) {

        // retrieve json object based on user id
        JsonObject resp = service.getSavedArticle(id);

        if (resp == null) {
            JsonObject error = Json.createObjectBuilder()
                .add("error", "Cannot find news article " + id)
                .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }

        return ResponseEntity.ok(resp.toString());
    }
}
