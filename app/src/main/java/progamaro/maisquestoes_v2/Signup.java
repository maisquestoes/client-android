package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import progamaro.maisquestoes_v2.dto.SignupDTO;
import progamaro.maisquestoes_v2.helpers.Routes;

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
    private ProgressDialog _progressDialog;

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

                    Signup_Register(_signupDTO);

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void Signup_Register(final SignupDTO pSignup) {

        startProgressBar();

        StringRequest request = new StringRequest(Request.Method.POST, Routes.SIGNUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                _progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
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
                params.put("lastName", pSignup.lastName);
                params.put("firstName", pSignup.firstName);
                params.put("email", pSignup.email);
                params.put("username", pSignup.username);
                params.put("password", "pass@@");


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
        _progressDialog = new ProgressDialog(Signup.this);
        _progressDialog.setTitle("Cadastro");
        _progressDialog.setMessage("Aguarde...");
        _progressDialog.setIndeterminate(false);
        _progressDialog.show();
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
