package progamaro.maisquestoes_v2.dto;

/**
 * Created by helio on 26/07/15.
 */
public class SigninDTO {

    //private String firstName;
    //private String lastName;
    private String[] apikey;
    private String email;
    private String[] roles;

    public SigninDTO() {
    }

    public SigninDTO(String[] apikey, String email, String[] roles) {
        this.apikey = apikey;
        this.email = email;
        this.roles = roles;
    }

    public String[] getApikey() {
        return apikey;
    }

    public void setApikey(String[] apikey) {
        this.apikey = apikey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

}
