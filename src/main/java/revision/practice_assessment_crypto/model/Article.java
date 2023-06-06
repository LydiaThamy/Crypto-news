package revision.practice_assessment_crypto.model;

import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Article implements Serializable {
    private String id;
    private long published_on;
    private String imageurl;
    private String title;
    private String url;
    private String body;
    private String tags;
    private String categories;

    // constructor
    public Article() {}

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPublished_on() {
        return published_on;
    }

    public void setPublished_on(long published_on) {
        this.published_on = published_on;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    // toString method
    @Override
    public String toString() {
        return "Article [id=" + id + ", published_on=" + published_on + ", title=" + title + ", url=" + url
                + ", imageUrl=" + imageurl + ", body=" + body + ", tags=" + tags + ", categories=" + categories + "]";
    }

    // create object from JSON response
    public static Article createArticle(JsonObject jObj) {

        Article article = new Article();

        article.setId(jObj.getString("id"));
        article.setPublished_on(jObj.getJsonNumber("published_on").longValue());
        article.setImageurl(jObj.getString("imageurl"));
        article.setTitle(jObj.getString("title"));
        article.setUrl(jObj.getString("url"));
        article.setBody(jObj.getString("body"));
        article.setTags(jObj.getString("tags"));
        article.setCategories(jObj.getString("categories"));

        return article;
    }

    public static List<Article> createList(String json) {
        
        List<Article> articles = new ArrayList<>();
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jObj = reader.readObject();
        JsonArray jArr = jObj.getJsonArray("Data");
        
        for (int i = 0; i < jArr.size(); i++) {
            JsonObject o = jArr.getJsonObject(i);
            Article article = Article.createArticle(o);
            articles.add(article);
        }

        return articles;
    }

    public static JsonObject toJson(Article a){

        return Json.createObjectBuilder()
        .add("id", a.getId())
        .add("published_on", a.getPublished_on())
        .add("imageurl", a.getImageurl())
        .add("title", a.getTitle())
        .add("url", a.getUrl())
        .add("body", a.getBody())
        .add("tags", a.getTags())
        .add("categories", a.getCategories())
        .build();
    }
}
