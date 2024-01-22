package ds.testingsystem.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int countOfQuestion;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

    public String toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", this.id);
        jsonObject.addProperty("name", this.name);
        jsonObject.addProperty("countOfQuestion", this.countOfQuestion);
        return new Gson().toJson(jsonObject);
    }
}
