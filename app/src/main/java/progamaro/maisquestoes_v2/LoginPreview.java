package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by andremiranda on 30/07/15.
 */
public class LoginPreview extends Activity {

    private Button btn_login_preview_enter;
    private Button btn_login_preview_signup;

    // Facebook
    private LoginButton btn_login_preview_fb;
    CallbackManager callbackManager;

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

        btn_login_preview_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Toast.makeText(getActivity(), "Facebook Logado OK!!!!", Toast.LENGTH_SHORT).show();
                Log.i("FACEBOOK", "Sucesso!!!");
            }

            @Override
            public void onCancel() {
                Log.i("FACEBOOK", "Cancelado!!!");
            }

            @Override
            public void onError(FacebookException e) {
                Log.i("FACEBOOK", "Erro!!!");
            }
        });

    }



    public void Init(){
        btn_login_preview_enter = (Button) findViewById(R.id.btn_login_preview_enter);
        btn_login_preview_signup = (Button) findViewById(R.id.btn_login_preview_signup);
        btn_login_preview_fb = (LoginButton)findViewById(R.id.btn_login_preview_fb);

        btn_login_preview_fb.setReadPermissions("public_profile","email");
        callbackManager = CallbackManager.Factory.create();
//        btn_login_preview_fb.setReadPermissions("user_friends");
    }
}
