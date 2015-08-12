package progamaro.maisquestoes_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import progamaro.maisquestoes_v2.adapters.SubjectsViewAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
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

        _gridview = (GridView) findViewById(R.id.gv_cards);
        _gridview.setAdapter(new SubjectsViewAdapter(getSubjects(), this));
    }

    private void GetSubjects() {
        StringRequest request = new StringRequest(Request.Method.GET, Routes.ALL_SUBJECTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error: "+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }

    public List<SubjectsDTO> getSubjects(){
        List<SubjectsDTO> list = new ArrayList<SubjectsDTO>();
        list.add(new SubjectsDTO("teste1", "teste_desc1"));
        list.add(new SubjectsDTO("teste2", "teste_desc2"));
        list.add(new SubjectsDTO("teste3", "teste_desc3"));
        list.add(new SubjectsDTO("teste4", "teste_desc4"));
        list.add(new SubjectsDTO("teste3", "teste_desc3"));
        list.add(new SubjectsDTO("teste4", "teste_desc4"));

        return list;
    }

    public void init(){
        _toolbar = (Toolbar) findViewById(R.id.preconfiguration_toolbar);

        setSupportActionBar(_toolbar);
        _toolbar.setNavigationIcon(R.mipmap.ic_search);
        _toolbar.setTitle("Pré Configuração");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pre_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_forward) {
            startActivity(new Intent(PreConfiguration.this, MainActivity_Drawer.class));
        }
        if (id == R.id.home) {
            Toast.makeText(this, "teste home button", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
