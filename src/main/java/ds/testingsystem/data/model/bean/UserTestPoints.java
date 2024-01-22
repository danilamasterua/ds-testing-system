package ds.testingsystem.data.model.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.User;
import jakarta.persistence.*;

@Entity
public class UserTestPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Test test;
    private double score;
    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("user_id", this.user.getId());
        jsonObject.addProperty("test_id", this.test.getId());
        jsonObject.addProperty("score", this.score);
        return new Gson().toJson(jsonObject);
    }
}
