package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import progamaro.maisquestoes_v2.domain.User;
import progamaro.maisquestoes_v2.dto.SigninDTO;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;

/**
 * Created by helio on 16/07/15.
 */
public class Login extends AppCompatActivity {

    private User user;

    private Toolbar inc_login_toolbar;
    private Button btn_login_mq;
    private EditText et_login_frag_user;
    private EditText et_login_frag_pass;

    // Facebook
    private LoginButton btn_login_fb;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;

    private ProgressDialog _progressDialog;

    private void init(){
        btn_login_fb = (LoginButton)findViewById(R.id.btn_login_fb);
        btn_login_mq = (Button)findViewById(R.id.btn_login_mq);
        et_login_frag_user = (EditText)findViewById(R.id.et_login_frag_user);
        et_login_frag_pass = (EditText)findViewById(R.id.et_login_frag_pass);
        inc_login_toolbar = (Toolbar)findViewById(R.id.inc_login_toolbar);

        btn_login_fb.setReadPermissions("public_profile","email");
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_frag);

        init();

        SigninDTO _sign = (SigninDTO)Preferences.getObjectPreference(getApplicationContext(), Preferences.LOGIN_PREFERENCES, new SigninDTO());
        Toast.makeText(getApplicationContext(), _sign.getApikey() + " - " + _sign.getEmail(), Toast.LENGTH_SHORT).show();

        setSupportActionBar(inc_login_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_login_mq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new loginAsync().execute();
                String _username = et_login_frag_user.getText().toString();
                String _pass = et_login_frag_pass.getText().toString();
                Signin(_username, _pass);
                //Authenticate("helio1234", "123456");
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken1) {

            }
        };
        accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken != null){
            startActivity(new Intent(Login.this, MainActivity_Drawer.class));
        }

        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                Log.i("FACEBOOK", "Success!!!");
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback(){
                            @Override
                            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                                try {
                                    user = new User(
                                            jsonObject.getString("id"),
                                            jsonObject.getString("name"),
                                            jsonObject.getString("email"),
                                            jsonObject.getString("gender")
                                                            );
                                    Toast.makeText(Login.this, "Hello, " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    Log.v("FACEBOOK", "Info: id: " + user.getId() + "; name: " + user.getDisplayName() + "; email: " + user.getEmail() + "; gender: " + user.getGender());
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
                Log.i("FACEBOOK", "Cancel!!!");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.i("FACEBOOK", "Error!!!");
            }
        });

    }

    private void Signin(final String pUsername, final String pPass) {

        startProgressBar();

        StringRequest request = new StringRequest(Request.Method.POST, Routes.SIGNIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JsonParser _jsonParser =new JsonParser();
                JsonObject _jsonObject = (JsonObject)_jsonParser.parse(response);

                SigninDTO _signinDTO = new Gson().fromJson(_jsonObject.get("o"), SigninDTO.class);

                Preferences.setObjectPreference(getApplicationContext(),Preferences.LOGIN_PREFERENCES, _signinDTO);

                Toast.makeText(Login.this, _signinDTO.getEmail(), Toast.LENGTH_SHORT).show();

                _progressDialog.dismiss();

                startActivity(new Intent(Login.this, MainActivity_Drawer.class));
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                _progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String,String>();
                params.put("username", pUsername);
                params.put("password", pPass);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                //params.put("Content-Type", "application/json");
                params.put("Content-Type","application/x-www-form-urlencoded");

                return params;
            }

            /*@Override
            public String getBodyContentType() {

                return "application/x-www-form-urlencoded";
            }*/
        };
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private void startProgressBar(){
        _progressDialog = new ProgressDialog(Login.this);
        _progressDialog.setTitle(getResources().getString(R.string.msg_logging));
        _progressDialog.setMessage(getResources().getString(R.string.msg_wait));
        _progressDialog.setIndeterminate(false);
        _progressDialog.show();
    }

    private void checkUserPassEmpty() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    /*class loginAsync extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {

            UserData userData = new UserData(Login.this);
            String _username = et_login_frag_user.getText().toString();
            String _pass = et_login_frag_pass.getText().toString();
            //String user = userData.getUser(_username, _pass);

            //return user;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

    }*/
}
