package ds.testingsystem.data.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int minutesToPass;
    @Length(max = 5000)
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<TestModule> modules = new HashSet<>();

    public String toJson(){
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("name", this.name);
        jo.addProperty("description", this.description);
        jo.addProperty("owner_id", this.owner.getId());
        return new Gson().toJson(jo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"id\":").append(this.id).
                append("\"name\":").append(this.name).
                append("\"description\":").append(this.description);
        return sb.toString();
    }
}
