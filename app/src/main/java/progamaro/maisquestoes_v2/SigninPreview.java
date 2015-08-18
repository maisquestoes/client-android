package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import progamaro.maisquestoes_v2.dto.SigninDTO;

/**
 * Created by andremiranda on 30/07/15.
 */
public class SigninPreview extends Activity {

    private Button btn_login_preview_enter;
    private Button btn_login_preview_signup;

    // Facebook
    private LoginButton btn_login_preview_fb;
    private CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signin_preview);
        Init();

        btn_login_preview_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninPreview.this, Signin.class));
            }
        });

        btn_login_preview_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SigninPreview.this, Signup.class));
            }
        });

        btn_login_preview_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback(){
                            @Override
                            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                Log.d("FACEBOOK", "Login Success!!!");
                                // Send request to server TODO

                                SigninDTO _signinDTO = new SigninDTO();
                                try {
                                    _signinDTO.setApikey(jsonObject.getString("id"));
                                    _signinDTO.setEmail(jsonObject.getString("email"));
                                    _signinDTO.setRoles(new String[]{"user"});
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );
                Bundle params = new Bundle();
                params.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(params);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.i("FACEBOOK", "Cancell!!!");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (accessTokenTracker != null) {
            accessTokenTracker.stopTracking();
        }
    }
}
