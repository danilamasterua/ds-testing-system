package ds.testingsystem.data.model.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.Test;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestGroupAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Test test;
    private LocalDateTime startAccessDateTime;
    private LocalDateTime endAccessDateTime;
    public String toJson(){
        JsonObject jo = new JsonObject();
        jo.addProperty("id", this.id);
        jo.addProperty("test_id", this.test.getId());
        jo.addProperty("startAccessDateTime", this.startAccessDateTime.toString());
        jo.addProperty("endAccessDateTime", this.endAccessDateTime.toString());
        return new Gson().toJson(jo);
    }
}
