package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;

/**
 * Created by helio on 18/07/15.
 */
public class Splash_screen extends Activity {

    private static final int SPLASH_TIME_OUT = 3000;

    //private AccessToken accessToken;
    SharedPreferences sharedPreferences;
    //AccessTokenTracker _accessTokenTracker;
    AccessToken _accesToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.splash_screen);

        updateWithToken(AccessToken.getCurrentAccessToken());

        /*_accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken pOldAccessToken, AccessToken pNewAccessToken) {
                //updateWithToken();

                Log.d("FACEBOOK", "old token: " + (pOldAccessToken != null ? pOldAccessToken.getToken() : "Sem OLD Token") );
                Log.d("FACEBOOK", "new token: " + (pNewAccessToken != null ? pNewAccessToken.getToken() : "Sem NEW Token") );

                updateWithToken(pNewAccessToken);
            }
        };*/

        /*Thread timerThread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    // 1 - Check is logged facebook
                    // Check how provider (facebook or local)
                    sharedPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
                    String provider = sharedPreferences.getString("prefUserProvider", "");
                    if (provider.isEmpty()){
                        // go signin_local screen
                        Intent it = new Intent(Splash_screen.this, SigninPreview.class);
                        //Intent it = new Intent(Splash_screen.this, Signup.class);
                        startActivity(it);
                    } else {
                        // check facebook or local
                        if (provider.equals("facebook")){
                            // provider FACEBOOK
                            //accessToken = AccessToken.getCurrentAccessToken();

                            *//*if (accessToken != null){
                                startActivity(new Intent(Splash_screen.this, MainActivity_Drawer.class));
                            } else {
                                // check is logged provider local

                            }*//*

                        } else {
                            // provider LOCAL
                            Intent it = new Intent(Splash_screen.this, MainActivity_Drawer.class);
                            startActivity(it);

                        }
                    }
                }
            }
        };
        timerThread.start();*/
    }

    private void updateWithToken(AccessToken pCurrentAccessToken) {

        // Logged by facebook
        if (pCurrentAccessToken != null){
            Thread _thread = new Thread(){
                @Override
                public void run() {
                    try{
                        sleep(SPLASH_TIME_OUT);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        Intent it = new Intent(Splash_screen.this, MainActivity_Drawer.class);
                        //Intent it = new Intent(Splash_screen.this, SigninPreview.class);
                        startActivity(it);
                        finish();
                    }
                }
            };
            _thread.start();
        } else {
            Thread _thread = new Thread(){
                @Override
                public void run() {
                    try{
                        sleep(SPLASH_TIME_OUT);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        Intent it = new Intent(Splash_screen.this, SigninPreview.class);
                        startActivity(it);
                        finish();
                    }
                }
            };
            _thread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
