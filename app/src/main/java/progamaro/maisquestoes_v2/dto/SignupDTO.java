package progamaro.maisquestoes_v2.dto;

import android.content.Context;
import android.content.res.Resources;

import progamaro.maisquestoes_v2.R;

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

    public SignupDTO() {
    }

    public SignupDTO(String username, String firstName, String lastName, String email, String password, String confirmPassword) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public void Validate(Context ctx) throws Exception {
        StringBuilder _str = new StringBuilder();

        if (username.isEmpty()) {
            _str.append(ctx.getResources().getString(R.string.err_signup_username_empty));
            _str.append("\n");
        }
        if (firstName.isEmpty()){
            _str.append(ctx.getResources().getString(R.string.err_signup_firstname_empty));
            _str.append("\n");
        }
        if (email.isEmpty()){
            _str.append(ctx.getResources().getString(R.string.err_signup_email_empty));
            _str.append("\n");
        }
        if (password.isEmpty()){
            _str.append(ctx.getResources().getString(R.string.err_signup_password_empty));
            _str.append("\n");
        }
        if (confirmPassword.isEmpty()){
            _str.append(ctx.getResources().getString(R.string.err_signup_confirmpassword_empty));
        }

        if (!_str.toString().isEmpty()) {
            throw new Exception(_str.toString());
        }
    }


}
