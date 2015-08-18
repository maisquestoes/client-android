package progamaro.maisquestoes_v2.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by helio on 31/07/15.
 */
public class Preferences {

    public static String LOGIN_PREFERENCES = "LOGIN_PREFERENCE";

    public static void setObjectPreference(Context ctx, String pPrefName, Object pObj){
        SharedPreferences _prefs = ctx.getSharedPreferences(pPrefName, ctx.MODE_PRIVATE);
        SharedPreferences.Editor _editor = _prefs.edit();
        Gson _gson = new Gson();
        String _json = _gson.toJson(pObj);
        _editor.putString(pObj.getClass().getSimpleName(), _json);
        _editor.commit();

        Toast.makeText(ctx, "Shared preference salvo com sucesso", Toast.LENGTH_LONG).show();
    }

    public static String getApiKey(Context ctx){
        //return ((SigninDTO) getObjectPreference(ctx, LOGIN_PREFERENCES, new SigninDTO())).getApikey();
        return "6E48182D9C98A25E1F1A8F"; // comments after (está dando pau na apikey de login)
    }

    public static Object getObjectPreference(Context ctx, String pPrefName, Object pObj){
        SharedPreferences _prefs = ctx.getSharedPreferences(pPrefName, ctx.MODE_PRIVATE);
        String _json = _prefs.getString(pObj.getClass().getSimpleName(), "");

        return new Gson().fromJson(_json, pObj.getClass());
    }

    public static void clearObjectPreference(Context ctx, String pPrefName, Object pObj){
        SharedPreferences _prefs = ctx.getSharedPreferences(pPrefName, ctx.MODE_PRIVATE);
        SharedPreferences.Editor _editor = _prefs.edit();
        _editor.remove(pObj.getClass().getSimpleName());
        _editor.commit();
    }

}
