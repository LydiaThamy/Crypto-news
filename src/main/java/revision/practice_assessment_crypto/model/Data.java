// package revision.practice_assessment_crypto.model;

// import java.io.StringReader;
// import java.util.ArrayList;
// import java.util.List;

// import jakarta.json.Json;
// import jakarta.json.JsonObject;
// import jakarta.json.JsonReader;

// public class Data {
//     private List<Article> articles;
    
//     // constructor
//     public Data() {}

//     // getter and setter
//     public List<Article> getArticles() {
//         return articles;
//     }
    
//     public void setArticles(List<Article> articles) {
//         this.articles = articles;
//     }

//     // toString method
//     @Override
//     public String toString() {
//         return "Data [articles=" + articles + "]";
//     }

//     // create object from JSON response
//     public static List<Article> createData(String json) {
//         List<Article> articles = new ArrayList<>();

//         JsonReader reader = Json.createReader(new StringReader(json));
//         JsonObject jObj = reader.readObject();

//         articles.setArticles(jObj.getJsonArray("Data")
//                 .stream()
//                 .map(o -> (JsonObject) o)
//                 .map(o -> Article.createArticle(o))
//                 .toList()
//                 );
        
//         return articles;
//     }
// }

