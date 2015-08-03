package progamaro.maisquestoes_v2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by helio on 16/07/15.
 */
public class Login_Frag extends Fragment {

    private EditText et_login_frag_user;
    private EditText et_login_frag_pass;
    private Button btn_login_mq;
    private LoginButton btn_login_fb;

    CallbackManager callbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_local, null);

        btn_login_fb = (LoginButton)view.findViewById(R.id.btn_login_fb);
        btn_login_mq = (Button)view.findViewById(R.id.btn_login_mq);

        btn_login_fb.setReadPermissions("user_friends");
        btn_login_fb.setFragment(this);

        callbackManager = CallbackManager.Factory.create();

        btn_login_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
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

        return view;
    }

}
