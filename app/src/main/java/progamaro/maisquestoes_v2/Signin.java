package progamaro.maisquestoes_v2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.FacebookSdk;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import progamaro.maisquestoes_v2.domain.User;
import progamaro.maisquestoes_v2.dto.LoginDTO;
import progamaro.maisquestoes_v2.dto.SigninDTO;
import progamaro.maisquestoes_v2.helpers.GsonHelper;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;
import progamaro.maisquestoes_v2.helpers.VolleyApplication;

/**
 * Created by helio on 16/07/15.
 */
public class Signin extends AppCompatActivity {

    private User user;

    private Toolbar inc_login_toolbar;
    private Button btn_login_mq;
    private EditText et_login_frag_user;
    private EditText et_login_frag_pass;

    private ProgressDialog _progressDialog;

    private void init(){
        btn_login_mq = (Button)findViewById(R.id.btn_login_mq);
        et_login_frag_user = (EditText)findViewById(R.id.et_login_frag_user);
        et_login_frag_pass = (EditText)findViewById(R.id.et_login_frag_pass);
        inc_login_toolbar = (Toolbar)findViewById(R.id.inc_login_toolbar);

        setSupportActionBar(inc_login_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // Find KeyHash for facebook
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo("progamaro.maisquestoes_v2", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signin_local);

        init();

        SigninDTO _sign = (SigninDTO)Preferences.getObjectPreference(getApplicationContext(), Preferences.LOGIN_PREFERENCES, new SigninDTO());
        if (_sign != null){
            Toast.makeText(getApplicationContext(), _sign.getApikey() + " - " + _sign.getEmail(), Toast.LENGTH_SHORT).show();
        }

        btn_login_mq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginDTO _loginDTO = new LoginDTO();
                _loginDTO.setUsername(et_login_frag_user.getText().toString());
                _loginDTO.setPassword(et_login_frag_pass.getText().toString());

                Signin(_loginDTO);
            }
        });

    }

    private void Signin(final LoginDTO pLoginDTO) {

        startProgressBar();

        final String json = new Gson().toJson(pLoginDTO);

        StringRequest request = new StringRequest(Request.Method.POST, Routes.SIGNIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SigninDTO _signinDTO = (SigninDTO) GsonHelper.fromJson(response, new SigninDTO());

                Preferences.setObjectPreference(getApplicationContext(),Preferences.LOGIN_PREFERENCES, _signinDTO);

                Toast.makeText(Signin.this, _signinDTO.getEmail(), Toast.LENGTH_SHORT).show();

                _progressDialog.dismiss();
                finish();

                startActivity(new Intent(Signin.this, PreConfiguration.class));
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
                return GsonHelper.getParams(json);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                //params.put("Content-Type", "application/json");
                params.put("Content-Type","application/x-www-form-urlencoded");

                return params;
            }

        };
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    private void startProgressBar(){
        _progressDialog = new ProgressDialog(Signin.this);
        _progressDialog.setTitle(getResources().getString(R.string.msg_logging));
        _progressDialog.setMessage(getResources().getString(R.string.msg_wait));
        _progressDialog.setIndeterminate(false);
        _progressDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
