package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.facebook.AccessToken;

/**
 * Created by helio on 18/07/15.
 */
public class Splash_screen extends Activity {

    private AccessToken accessToken;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timerThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    // Check how provider (facebook or local)
                    sharedPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
                    String provider = sharedPreferences.getString("prefUserProvider", "");
                    if (provider.isEmpty()){
                        // go login screen
                        Intent it = new Intent(Splash_screen.this, LoginPreview.class);
                        //Intent it = new Intent(Splash_screen.this, Signup.class);
                        startActivity(it);
                    } else {
                        // check facebook or local
                        if (provider.equals("facebook")){
                            // provider FACEBOOK
                            accessToken = AccessToken.getCurrentAccessToken();

                            if (accessToken != null){
                                startActivity(new Intent(Splash_screen.this, MainActivity_Drawer.class));
                            } else {
                                // check is logged provider local

                            }

                        } else {
                            // provider LOCAL
                            Intent it = new Intent(Splash_screen.this, MainActivity_Drawer.class);
                            startActivity(it);

                        }
                    }
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
