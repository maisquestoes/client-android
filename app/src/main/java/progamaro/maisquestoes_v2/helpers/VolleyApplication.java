package progamaro.maisquestoes_v2.helpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import progamaro.maisquestoes_v2.domain.User;

/**
 * Created by helio on 18/07/15.
 */
public class VolleyApplication extends Application {

    private static VolleyApplication sInstance;
    private RequestQueue mRequestQueue;
    public static enum provider {
        local, facebook
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);
        sInstance = this;
    }

    public synchronized static VolleyApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public static User getUserLogged(Context ctx){
        SharedPreferences _settings;
        String PREF_NAME = "LoginPreferences";
        _settings = ctx.getSharedPreferences(PREF_NAME, 0);
        String _userPref = _settings.getString("prefUserName", "");
        if (!_userPref.isEmpty()) {
            User _user = new User();
            _user.setDisplayName(_settings.getString("prefName", ""));
            _user.setUsername(_settings.getString("prefUserName", ""));
            _user.setId("");
            _user.setEmail(_settings.getString("prefUserEmail", ""));

            //Log.i("PREFERENCES", _userPref);
            return _user;
        }

        return null;
    }

    public static void setSharedPreferences(Context ctx, String pPREF_NAME, String pPref, String pValue){
        SharedPreferences _preferences = ctx.getSharedPreferences(pPREF_NAME, 0);
        SharedPreferences.Editor editor = _preferences.edit();
        editor.putString(pPref, pValue);
        editor.commit();
    }

    public static void clearSharedPreferences(Context ctx, String pPREF_NAME, String pPref){
        SharedPreferences _preferences = ctx.getSharedPreferences(pPREF_NAME, 0);
        SharedPreferences.Editor _editor = _preferences.edit();
        _editor.remove(pPref);
        _editor.commit();
    }

}
