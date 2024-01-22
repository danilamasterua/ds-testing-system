package ds.testingsystem.data.model.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Question;
import ds.testingsystem.data.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserQuestionPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User user;
    private double score;
    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("question_id", this.question.getId());
        jsonObject.addProperty("user_id", this.user.getId());
        jsonObject.addProperty("score", this.score);
        return new Gson().toJson(jsonObject);
    }
}
