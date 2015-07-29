package progamaro.maisquestoes_v2.dto;

/**
 * Created by andremiranda on 28/07/15.
 */
public class SignupDTO {
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;

    public SignupDTO(String username, String firstName, String lastName, String email, String password, String confirmPassword) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
