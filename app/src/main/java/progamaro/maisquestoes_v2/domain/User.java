package progamaro.maisquestoes_v2.domain;

/**
 * Created by helio on 16/07/15.
 */
public class User {

    public String id;
    public String username;
    public String displayName;
    public String email;
    public String gender;
    private String created;
    private enum provider {
        local, facebook
    }
    private boolean verified;
    private String accessToken;

    public User() {
    }

    public User(String id, String displayName, String email, String gender) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.gender = gender;
    }

    public User(String id, String username, String displayName, String email, String gender) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.gender = gender;
    }
}
