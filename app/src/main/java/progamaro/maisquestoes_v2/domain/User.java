package progamaro.maisquestoes_v2.domain;

/**
 * Created by helio on 16/07/15.
 */
public class User {

    private String id;
    private String username;
    private String displayName;
    private String email;
    private String gender;
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

    public User(String id, String username, String displayName, String email, String gender, String created, boolean verified, String accessToken) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.email = email;
        this.gender = gender;
        this.created = created;
        this.verified = verified;
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
