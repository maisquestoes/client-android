package progamaro.maisquestoes_v2.dto;

import android.content.Context;

import progamaro.maisquestoes_v2.R;

/**
 * Created by andremiranda on 28/07/15.
 */
public class SignupDTO {
    public String username;
    public String name;
    public String email;
    public String password;
    public String confirmPassword;

    public SignupDTO() {
    }

    public SignupDTO(String username, String name, String email, String password, String confirmPassword) {

        this.username = username;
        this.name = name;
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
        if (name.isEmpty()){
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
        if (!_str.toString().isEmpty()) {
            throw new Exception(_str.toString());
        }
    }


}
