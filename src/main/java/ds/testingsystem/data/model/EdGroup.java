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
@Table
@Getter
@Setter
@NoArgsConstructor
public class EdGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_group",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();
    public String toJson(){
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("groupName", this.groupName);
        return new Gson().toJson(jo);
    }
}
