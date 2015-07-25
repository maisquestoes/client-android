package progamaro.maisquestoes_v2.data;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by helio on 18/07/15.
 */
public class UserData {

    private RequestQueue mRequestQueue;
    private Context context;

    public UserData(Context ctx) {
        this.context = ctx;
    }

    public void getUser(String pUserName, String pPass){

        String url = "http://api.maisquestoes.com.br/usuario/autenticar/"+ pUserName + "/" + pPass;
        mRequestQueue = Volley.newRequestQueue(context);
        final String[] _username = new String[1];
        JsonObjectRequest request = new JsonObjectRequest(url,null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                try {
                    _username[0] = response.getJSONObject("o").getString("Nome");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(request);
    }
}
