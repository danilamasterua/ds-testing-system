package ds.testingsystem.data.model;

import com.google.gson.Gson;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AnswerVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean isRight;

    public String toJson(){
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return "\"id\":" + this.id +
                "\"description\":" + this.description +
                "\"isRight\":" + this.isRight;
    }
}
