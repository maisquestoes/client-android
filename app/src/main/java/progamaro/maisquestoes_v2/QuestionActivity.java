package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import progamaro.maisquestoes_v2.adapters.AnswerRecyler;
import progamaro.maisquestoes_v2.drawer.SimpleDividerItemDecoration;
import progamaro.maisquestoes_v2.dto.QuestionDTO;
import progamaro.maisquestoes_v2.helpers.GsonHelper;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;
import progamaro.maisquestoes_v2.helpers.VolleyApplication;

/**
 * Created by helio on 27/10/15.
 */
public class QuestionActivity extends AppCompatActivity {

    private RecyclerView _rv_questions_answers;
    private TextView _tv_question_text;
    private TextView _tv_question_chrono;
    private AnswerRecyler _answerRecyler;
    private ImageView _iv_toolbar_thumbup;
    private ImageView _iv_toolbar_thumbdown;

    private int _seconds_remaining = 0;
    private int _minutes_remaining = 0;
    private int _minutes_aux = 0;
    private int _seconds_aux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.questions);
        init();
        requestQuestion();

        _iv_toolbar_thumbdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });

        _iv_toolbar_thumbup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });

        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                _minutes_aux = (int)(millisUntilFinished/1000)/60;
                _seconds_aux = (int)(millisUntilFinished/1000)%60;

                _tv_question_chrono.setText(String.format("%02d:%02d",_minutes_aux,_seconds_aux));
            }

            @Override
            public void onFinish() {
                _tv_question_chrono.setText("Done!");

                Dialog _dialog = new Dialog(QuestionActivity.this);
                _dialog.setContentView(R.layout.question_advertise);
                _dialog.show();
            }
        }.start();
    }

    private void initData(QuestionDTO pQuestion) {

        _tv_question_text.setText(pQuestion.getQuery());
        _answerRecyler = new AnswerRecyler(QuestionActivity.this, pQuestion.answers);
        _rv_questions_answers.setAdapter(_answerRecyler);

    }

    private void init() {
        _iv_toolbar_thumbup = (ImageView) findViewById(R.id.iv_toolbar_thumbup);
        _iv_toolbar_thumbdown = (ImageView) findViewById(R.id.iv_toolbar_thumbdown);

        _rv_questions_answers = (RecyclerView) findViewById(R.id.rv_questions_answers);
        _rv_questions_answers.setLayoutManager(new LinearLayoutManager(this));
        _rv_questions_answers.addItemDecoration(new SimpleDividerItemDecoration(getResources()));

        _tv_question_text = (TextView) findViewById(R.id.tv_question_text);
        _tv_question_chrono = (TextView) findViewById(R.id.tv_question_chrono);
    }

    private void requestQuestion(){

        String objectIdQuestion = "553ced28396602a5602d3a93";

        final String apiKey = Preferences.getApiKey(QuestionActivity.this);

        StringRequest request = new StringRequest(Request.Method.GET, Routes.QUESTION + objectIdQuestion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //SigninDTO _signinDTO = (SigninDTO) GsonHelper.fromJson(response, new SigninDTO());
                QuestionDTO _questionDTO = (QuestionDTO) GsonHelper.fromJson(response, new QuestionDTO());

                //Preferences.setObjectPreference(getApplicationContext(), Preferences.LOGIN_PREFERENCES, _signinDTO);
                initData(_questionDTO);
                Toast.makeText(QuestionActivity.this, "Request Success", Toast.LENGTH_SHORT).show();

                //_progressDialog.dismiss();
                //finish();

                //startActivity(new Intent(Signin.this, PreConfiguration.class));
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //_progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                //params.put("Content-Type", "application/json");
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("apiKey", apiKey);

                return params;
            }

        };
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
