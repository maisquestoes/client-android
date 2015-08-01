package progamaro.maisquestoes_v2.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import progamaro.maisquestoes_v2.dto.SigninDTO;

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

    public static Object getObjectPreference(Context ctx, String pPrefName, Object pObj){
        SharedPreferences _prefs = ctx.getSharedPreferences(pPrefName, ctx.MODE_PRIVATE);
        String _json = _prefs.getString(pObj.getClass().getSimpleName(), "");

        Object _obj = new Gson().fromJson(_json, Object.class);

        return _obj;
    }

}
