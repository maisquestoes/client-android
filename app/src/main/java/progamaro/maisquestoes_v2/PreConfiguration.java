package progamaro.maisquestoes_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progamaro.maisquestoes_v2.adapters.SubjectsViewAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
import progamaro.maisquestoes_v2.helpers.GsonHelper;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;

/**
 * Created by andremiranda on 06/08/15.
 */
public class PreConfiguration extends AppCompatActivity {

    private GridView _gridview;
    private Toolbar _toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_configuration);

        init();

        GetSubjects();
    }

    private void GetSubjects() {
        final String apikey = Preferences.getApiKey(PreConfiguration.this);

        StringRequest request = new StringRequest(Request.Method.GET, Routes.ALL_SUBJECTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<SubjectsDTO> _listObjects = GsonHelper.fromJsonList(response, new SubjectsDTO());

                _gridview.setAdapter(new SubjectsViewAdapter(_listObjects, PreConfiguration.this));

//                Preferences.setObjectPreference(getApplicationContext(), Preferences.LOGIN_PREFERENCES, _signinDTO);

                //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                //params.put("Content-Type", "application/json");
                //params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("apikey", apikey);

                return params;
            }
        };
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    public void init(){
        _toolbar = (Toolbar) findViewById(R.id.preconfiguration_toolbar);

        setSupportActionBar(_toolbar);
        _toolbar.setNavigationIcon(R.mipmap.ic_search);
        _toolbar.setTitle("Pré Configuração");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.background_activities));

        _gridview = (GridView) findViewById(R.id.gv_cards);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pre_menu, menu);

        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(android.R.id.home).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_forward) {
            startActivity(new Intent(PreConfiguration.this, MainActivity_Drawer.class));
        }
        if (id == android.R.id.home) {
            Toast.makeText(this, "teste home button", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
