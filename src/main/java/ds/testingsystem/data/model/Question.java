package ds.testingsystem.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.bean.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 500)
    private String description;
    private String imgUrl;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<AnswerVariant> answerVariants = new HashSet<>();
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("description", this.description);
        jsonObject.addProperty("imgUrl", this.imgUrl);
        jsonObject.addProperty("qType", this.questionType.name());
        return new Gson().toJson(jsonObject);
    }
}
