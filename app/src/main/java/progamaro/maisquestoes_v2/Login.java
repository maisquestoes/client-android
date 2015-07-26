package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.AccessToken;
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

import progamaro.maisquestoes_v2.domain.User;

/**
 * Created by helio on 16/07/15.
 */
public class Login extends Activity {

    private User user;

    private Button btn_login_mq;
    public EditText et_login_frag_user;
    private EditText et_login_frag_pass;

    // Facebook
    private LoginButton btn_login_fb;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;

    private void init(){
        btn_login_fb = (LoginButton)findViewById(R.id.btn_login_fb);
        btn_login_mq = (Button)findViewById(R.id.btn_login_mq);
        et_login_frag_user = (EditText)findViewById(R.id.et_login_frag_user);
        et_login_frag_pass = (EditText)findViewById(R.id.et_login_frag_pass);
        btn_login_fb.setReadPermissions("public_profile","email");
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.login_frag);

        init();

        btn_login_mq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new loginAsync().execute();
                String _username = et_login_frag_user.getText().toString();
                String _pass = et_login_frag_pass.getText().toString();
                //Authenticate(_username, _pass);
                Authenticate("helio1234", "123456");
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken1) {

            }
        };
        accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken != null){
            startActivity(new Intent(Login.this, MainActivity.class));
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
                                    Toast.makeText(Login.this, "Hello, " + user.displayName, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    Log.v("FACEBOOK", "Info: id: " + user.id + "; name: " + user.displayName + "; email: " + user.email + "; gender: " + user.gender);
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

    private void Authenticate(final String pUserName, String pPass) {
        String url = "http://api.maisquestoes.com.br/usuario/autenticar/"+ pUserName + "/" + pPass;
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                // Check user logged is not empty
                User _userLogged = VolleyApplication.getUserLogged(Login.this);
                if (_userLogged == null) {

                    try {
                        JSONObject jsonObject = response.getJSONObject("o");
                        User _user = new User();
                        _user.username = jsonObject.getString("Usuario");
                        _user.displayName = jsonObject.getString("Nome");
                        _user.email = jsonObject.getString("Email");

                        VolleyApplication.setSharedPreferences(Login.this, "LoginPreferences", "prefUserName", _user.username);
                        VolleyApplication.setSharedPreferences(Login.this, "LoginPreferences", "prefName", _user.displayName);
                        VolleyApplication.setSharedPreferences(Login.this, "LoginPreferences", "prefUserEmail", _user.email);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Login.this, "username: " + _userLogged.username +
                            " name: " + _userLogged.displayName +
                            " email: " + _userLogged.email, Toast.LENGTH_SHORT).show();
                }

                // Set user in preferences logged
                //VolleyApplication.setSharedPreferences(Login.this,"LoginPreferences","prefUserName",pUserName);
                //VolleyApplication.setSharedPreferences(Login.this,"LoginPreferences","prefUserPass","");
                //VolleyApplication.setSharedPreferences(Login.this,"LoginPreferences","prefUserEmail","");

                startActivity(new Intent(Login.this, MainActivity.class));
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(request);
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
