package progamaro.maisquestoes_v2.helpers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andremiranda on 30/07/15.
 */
public class GsonHelper {

    public static HashMap<String, String> getParams(String json){
        Type stringStringMap = new TypeToken<HashMap<String, String>>(){}.getType();
        final HashMap<String,String> map = new Gson().fromJson(json, stringStringMap);
        return map;
    }

}
