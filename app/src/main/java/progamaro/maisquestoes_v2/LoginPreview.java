package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

/**
 * Created by andremiranda on 30/07/15.
 */
public class LoginPreview extends Activity {

    private Button btn_login_preview_enter;
    private Button btn_login_preview_signup;

    // Facebook
    private LoginButton btn_login_fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_preview);
        Init();

        btn_login_preview_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPreview.this, Login.class));
            }
        });

        btn_login_preview_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPreview.this, Signup.class));
            }
        });
    }

    public void Init(){
        btn_login_preview_enter = (Button) findViewById(R.id.btn_login_preview_enter);
        btn_login_preview_signup = (Button) findViewById(R.id.btn_login_preview_signup);

        btn_login_fb = (LoginButton)findViewById(R.id.btn_login_fb);
        btn_login_fb.setReadPermissions("public_profile","email");
    }
}
