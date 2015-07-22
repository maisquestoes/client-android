package domain;

/**
 * Created by helio on 16/07/15.
 */
public class User {

    public String id;
    public String username;
    public String name;
    public String email;
    public String gender;

    public User() {
    }

    public User(String id, String name, String email, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User(String id, String username, String name, String email, String gender) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
}
