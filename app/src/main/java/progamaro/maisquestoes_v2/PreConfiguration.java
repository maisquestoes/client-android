package progamaro.maisquestoes_v2;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progamaro.maisquestoes_v2.adapters.SubjectsViewAdapter;
import progamaro.maisquestoes_v2.dto.SubjectsDTO;
import progamaro.maisquestoes_v2.helpers.GsonHelper;
import progamaro.maisquestoes_v2.helpers.Preferences;
import progamaro.maisquestoes_v2.helpers.Routes;
import progamaro.maisquestoes_v2.helpers.VolleyApplication;
import progamaro.maisquestoes_v2.sqlite.DbOpenHelper;
import progamaro.maisquestoes_v2.sqlite.DbSubjectsFavoritesHelper;

/**
 * Created by andremiranda on 06/08/15.
 */
public class PreConfiguration extends AppCompatActivity {

    private GridView _gridview;
    private Toolbar _toolbar;
    private ProgressDialog _progressDialog;
    private SubjectsViewAdapter _subjectsViewAdapter;

    private DbOpenHelper _db;
    private SubjectsDTO _currentSubject;

    private List<SubjectsDTO> _listObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_configuration);

        init();
        initSearchView();

        GetSubjects();

        _gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView iv_icon_like = (ImageView) view.findViewById(R.id.iv_icon_like);

                _currentSubject = (SubjectsDTO) parent.getItemAtPosition(position);
                _currentSubject.setChecked(!iv_icon_like.isSelected());
                _subjectsViewAdapter.notifyDataSetChanged();
                _gridview.invalidateViews();

                // Add subject in sqlite
                new Favorites().execute("Update");

                //Toast.makeText(PreConfiguration.this, _subject.getSubject(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetSubjects() {

        startProgressBar();

        final String apikey = Preferences.getApiKey(PreConfiguration.this);

        StringRequest request = new StringRequest(Request.Method.GET, Routes.ALL_SUBJECTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                _listObjects = GsonHelper.fromJsonList(response, new SubjectsDTO());

                new Favorites().execute("InsertAll");

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

    private void startProgressBar(){
        _progressDialog = new ProgressDialog(PreConfiguration.this);
        _progressDialog.setTitle(getResources().getString(R.string.msg_load));
        _progressDialog.setMessage(getResources().getString(R.string.msg_wait));
        _progressDialog.setIndeterminate(false);
        _progressDialog.show();
    }

    public void initSearchView(){

        SearchView searchView = (SearchView) findViewById(R.id.pre_action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setFocusable(true);
        searchView.requestFocus();
        searchView.setIconified(false);
        searchView.onActionViewCollapsed();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ((SearchView) findViewById(R.id.pre_action_search)).onActionViewCollapsed();
                return true;
            }
        });

        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextChange(String newText)
            {
                List<SubjectsDTO> newSubjects = new ArrayList<>();

                for(SubjectsDTO s : _listObjects){
                    if(s.getSubject() != null && removeDiacriticalMarks(s.getSubject().toLowerCase()).contains(newText.toLowerCase()))
                        newSubjects.add(s);
                }

                _subjectsViewAdapter = new SubjectsViewAdapter(PreConfiguration.this,R.layout.card_subjects, newSubjects);
                _gridview.setAdapter(_subjectsViewAdapter);

                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                List<SubjectsDTO> newSubjects = new ArrayList<>();

                for(SubjectsDTO s : _listObjects){
                    if(s.getSubject() != null && removeDiacriticalMarks(s.getSubject().toLowerCase()).contains(query.toLowerCase()))
                        newSubjects.add(s);
                }

                _subjectsViewAdapter = new SubjectsViewAdapter(PreConfiguration.this,R.layout.card_subjects, newSubjects);
                _gridview.setAdapter(_subjectsViewAdapter);

                return false;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
    }

    public void init(){
        _toolbar = (Toolbar) findViewById(R.id.preconfiguration_toolbar);

        setSupportActionBar(_toolbar);
//        _toolbar.setNavigationIcon(R.mipmap.ic_search);
        _toolbar.setTitle("Pré Configuração");

//        mStatusView = new TextView(this);
//        mStatusView.setPadding(10, 10, 10, 10);
//        mStatusView.setText("Action Bar Usage example from CoderzHeaven");


//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        getSupportActionBar().setDisplayShowHomeEnabled(false);   //disable back button
//        getSupportActionBar().setHomeButtonEnabled(false);

        _gridview = (GridView) findViewById(R.id.gv_cards);

        _db = new DbOpenHelper(getApplicationContext());
    }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pre_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_forward) {
            startActivity(new Intent(PreConfiguration.this, MainActivity.class));
            finish();
        }
//        if (id == android.R.id.home) {
//            Toast.makeText(this, "teste home button", Toast.LENGTH_SHORT).show();
//        }

        return super.onOptionsItemSelected(item);
    }

    class Favorites extends AsyncTask<String, Integer, Long> {

        int _type; // 1 - Insert, 2 - Delete, 3 - InsertAll

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long result) {
            super.onPostExecute(result);

            if (result > 0) {
                if (_type == 1) {
                    Toast.makeText(PreConfiguration.this, "Adicionado", Toast.LENGTH_SHORT).show();
                } else if (_type == 2) {
                    Toast.makeText(PreConfiguration.this, "Removido", Toast.LENGTH_SHORT).show();
                } else if (_type == 3) {
                    _subjectsViewAdapter = new SubjectsViewAdapter(PreConfiguration.this,R.layout.card_subjects, _listObjects);
                    _gridview.setAdapter(_subjectsViewAdapter);

                    _progressDialog.dismiss();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(String... params) {

            DbSubjectsFavoritesHelper _dbFavorites = new DbSubjectsFavoritesHelper(_db);
            long result = 0;


            if (params[0] == "Update") {
                if (_currentSubject.isChecked()) {
                    result = _dbFavorites.Update(_currentSubject);
                    _type = 1;
                } else {
                    result = _dbFavorites.Update(_currentSubject);
                    _type = 2;
                }
            } else if (params[0] == "InsertAll"){
                _type = 3;
                _dbFavorites.DeleteAll();
                for (SubjectsDTO item : _listObjects) {
                    result = (_dbFavorites.Insert(item));
                }
                
            }

            return result;
        }
    }
}
