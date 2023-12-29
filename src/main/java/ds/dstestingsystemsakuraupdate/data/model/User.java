package ds.dstestingsystemsakuraupdate.data.model;


import ds.dstestingsystemsakuraupdate.data.model.bean.UserAccessLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@Table(name = "users")
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

    public void setPassword(String password) {
        this.password = DigestUtils.sha256Hex(password);
    }

    public User(String login, String password, String firstName, String lastName, String email, UserAccessLevel userAccessLevel) {
        this.login = login;
        this.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userAccessLevel = userAccessLevel;
    }

    public boolean checkPassword(String password){
        return this.password.equals(DigestUtils.sha256Hex(password));
    }
}
