package ds.testingsystem.data.model.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.AnswerVariant;
import ds.testingsystem.data.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private AnswerVariant answer;
    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("user_id", this.user.getId());
        jsonObject.addProperty("answer_id", this.answer.getId());
        return new Gson().toJson(jsonObject);
    }
}
