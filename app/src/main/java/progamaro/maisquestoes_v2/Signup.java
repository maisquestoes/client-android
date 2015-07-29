package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import progamaro.maisquestoes_v2.dto.SignupDTO;

/**
 * Created by helio on 28/07/15.
 */
public class Signup extends Activity {

    private EditText et_singup_username;
    private EditText et_singup_firstname;
    private EditText et_singup_lastname;
    private EditText et_singup_email;
    private EditText et_singup_pass;
    private EditText et_singup_confirmpass;
    private Button btn_signup_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        init();

        btn_signup_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignupDTO _signupDTO = new SignupDTO(et_singup_username.getText().toString(),
                                                    et_singup_firstname.getText().toString(),
                                                    et_singup_lastname.getText().toString(),
                                                    et_singup_email.getText().toString(),
                                                    et_singup_pass.getText().toString(),
                                                    et_singup_confirmpass.getText().toString());

                try {

                    _signupDTO.Validate(getApplicationContext());

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void init() {
        et_singup_username = (EditText) findViewById(R.id.et_singup_username);
        et_singup_firstname = (EditText) findViewById(R.id.et_singup_firstname);
        et_singup_lastname = (EditText) findViewById(R.id.et_singup_lastname);
        et_singup_email = (EditText) findViewById(R.id.et_singup_email);
        et_singup_pass = (EditText) findViewById(R.id.et_singup_pass);
        et_singup_confirmpass = (EditText) findViewById(R.id.et_singup_confirmpass);
        btn_signup_register = (Button) findViewById(R.id.btn_signup_register);

    }
}
