package ds.testingsystem.data.model;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.bean.UserAccessLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    @Length(max = 512)
    private String password;
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserAccessLevel userAccessLevel;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<EdGroup> groups = new HashSet<>();

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

    public boolean checkPassword(String password){
        return this.password.equals(DigestUtils.sha256Hex(password));
    }
    public String toJson(){
        JsonObject jo = new JsonObject();
        jo.addProperty("firstName", this.firstName);
        jo.addProperty("lastName", this.lastName);
        jo.addProperty("email", this.email);
        jo.addProperty("userAccessLevel", this.userAccessLevel.toString());
        JsonArray jarray = new JsonArray();
        this.groups.forEach(group->{
            jarray.add(group.toJson());
        });
        jo.addProperty("groups", jarray.toString());
        return new Gson().toJson(jo);
    }
}
